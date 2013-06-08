(ns clj-data-solutions.extract)

(use 'incanter.core
          'incanter.io
          'incanter.stats
          'clojure.data.json
          '[clojure.string :only (upper-case)])

(defn extract-csv [filepath]
    (read-dataset filepath :header true))

(defn extract-json [filepath]
   (to-dataset (read-json (slurp filepath))))
