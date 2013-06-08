(ns clj-data-solutions.transformations)

(use 'incanter.core
          'incanter.io
          'incanter.stats
          'clojure.data.json
          '[clojure.string :only (upper-case)])

(defn clean-weather-bouy-names [synonyms weather-bouy-name ]
    (let [upper-case-name (upper-case weather-bouy-name)]
          (synonyms upper-case-name upper-case-name)))


(defn extract-column-from-dataset [colname ds]
      ($ (keyword colname) ds))

(defn extract-row-number-from-dataset [rownum ds]
      ($ rownum ds))
