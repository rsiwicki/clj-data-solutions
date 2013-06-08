(ns clj-data-solutions.calculations)

(defn convert-string-to-0 [value]
    (if (string? value) 0 value))

(defn convert-metres-to-centimetres [value]
    (* value 100))

(defn convert-knots-to-metres-per-second [value]
    (* value 0.51444))
