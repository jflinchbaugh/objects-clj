(ns objects-clj.core
  (:require [scad-clj.model :refer :all]
            [scad-clj.scad :refer :all]))

(spit
 "demo.scad"
 (write-scad
  (fn! 60)
  (for [x (range 0 400 100)]
    (translate [x 0 0]
      (union
        (cube 100 100 100)
        (sphere 70))))))
