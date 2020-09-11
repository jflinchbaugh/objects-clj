(ns objects-clj.soap-dish
  (:refer-clojure :exclude [import use])
  (:require [objects-clj.core :refer [as-hole]]
            [scad-clj.model :refer :all]))

; 12 mm radius
; 55 mm from wall
; soap is 100mm x 60mm

(defn soap-dish [{:keys [radius width dish-width dish-length thickness]}]
  (fn! 48)
  (difference
   (union
    (cylinder (+ radius thickness) (+ (* 2 thickness) dish-length))
    (translate
     [(- 0 radius thickness thickness) (- width (/ dish-width 2) thickness) 0]
     (cube
      (* 4 thickness)
      (+ dish-width thickness thickness)
      (+ dish-length thickness thickness))))
   (cylinder radius (as-hole (+ dish-length thickness thickness)))
   (translate
    [0 (- 0 radius) 0]
    (cube
     (* 2 radius)
     (* 2 radius)
     (as-hole (+ dish-length thickness thickness))))
   (translate
    [(- 0 radius thickness thickness thickness) (- width (/ dish-width 2) thickness) 0]
    (cube (* 4 thickness) dish-width dish-length))
   (translate
    [(- 0 radius thickness thickness thickness)
     (- width (/ dish-width 2) thickness thickness)
     0]
    (cube
     (as-hole (* 2 thickness))
     (as-hole (+ dish-width thickness thickness))
     (as-hole (+ dish-length thickness thickness))))
   (for
    [cut1 (range (+ (/ dish-length -2) (/ dish-length 8)) (/ dish-length 2) (/ dish-length 8))
     cut2 (range 2)]
     (union
      (translate
       [(+ (- 0 radius thickness))
        (- width (* dish-width 1/4) thickness)
        cut1]
       (cube (as-hole (* 2 thickness)) (/ dish-width 3) (* 2 thickness)))
      (translate
       [(+ (- 0 radius thickness))
        (- width (* dish-width 3/4) thickness)
        cut1]
       (cube (as-hole (* 2 thickness)) (/ dish-width 3) (* 2 thickness)))))))

(defn builds []
  {:soap-dish (partial soap-dish {:radius 12 :width 55 :dish-width 60 :dish-length 100 :thickness 2})}
  )
