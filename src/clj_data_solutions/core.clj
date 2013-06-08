(ns clj-data-solutions.core)

(use 'incanter.core
     'incanter.io
     'incanter.stats
     'clojure.data.json
     '[clojure.string :only (upper-case)]
     'clj-data-solutions.load
     'clj-data-solutions.calculations
     'clj-data-solutions.transformations
     'clj-data-solutions.extract)

(def weather-bouy-name-synonyms
 {  "K5.0" "K5"
  , "K5 BOUY" "K5"
  , "K5 OBSERVATORY" "K5" 
  })

(def k5-marine-data 
   (extract-csv "data/met_k5_marine_offshore_bouy.csv"))

(def marine-data-all-json 
  (extract-json "data/latest-marine-observational-data.json"))

(defn pipeline 
  ([f keywordcolsvec dataset]
  (conj-cols dataset ($map f keywordcolsvec dataset))))

(defn run-pipeline [dataset] 
  (pipeline (fn [value] (clean-weather-bouy-names weather-bouy-name-synonyms value)) [:station]
  (pipeline convert-knots-to-metres-per-second [:col-2]
  (pipeline convert-string-to-0 [(keyword "wind speed (knots)")]
  (pipeline convert-metres-to-centimetres [:col-0]
  (pipeline (comp convert-metres-to-centimetres convert-string-to-0 ) [(keyword "wave height (metres)")] dataset))))))

(defn -main
  ([]
   (let [clean-data 
   (run-pipeline k5-marine-data)]
    (correlation ($ :col-1 clean-data) ($ :col-3 clean-data)))))

