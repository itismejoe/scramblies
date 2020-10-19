(ns scramblies.core-test
  (:require [clojure.test :refer :all]
            [scramblies.core :refer :all]))

(deftest scramble?-test01
  (is (scramble? "rekqodlw" "world")))

(deftest scramble?-test02
  (is (scramble? "cedewaraaossoqqyt" "codewars")))

(deftest scramble?-test03
  (not (scramble? "katas" "steak")))

(deftest scramble?-test04
  (is (scramble? "aaaaaaaasaaaaaaaaasaaaaaaa" "ss")))

(deftest scramble?-test05
  (not (scramble? "aaaaaaaasaaaaaaaaasaaaaaaa" "sss")))

(deftest check-if-small-letters-test1
  (is (valid-inputs? "ashdiuashduahsdiuahduiahda" "ashdiuhasuidhuiadhuiashduiahsdiuahsiau")))

(deftest check-if-small-letters-test2
  (not (valid-inputs? "ashdahsdiuAahduiahda" "ashdiuhasuiuahsiau")))

(deftest check-if-small-letters-test3
  (not (valid-inputs? "ashdahsdiuahduiahda" "ashdiuhasuAiuahsiau")))

(deftest check-if-small-letters-test4
  (not (valid-inputs? "ashdiuashduah1sdiuahduiahda" "ashdiuhasuidhuiadhuiashduiahsdiuahsiau")))

(deftest check-if-small-letters-test5
  (not (valid-inputs? "ashdiuashduahsdiuahduiahda" "ashdiuhasuidhuiadhuias1hduiahsdiuahsiau")))

(deftest check-if-small-letters-test6
  (not (valid-inputs? nil "ashdiuhasuidhuiadhuias1hduiahsdiuahsiau")))

(deftest check-if-small-letters-test7
  (not (valid-inputs? "ashdiuhasuidhuiadhuias1hduiahsdiuahsiau" nil)))

(deftest check-if-small-letters-test8
  (not (valid-inputs? "asdsdd asdsadas" "sadasdasdasdasd")))

(deftest check-if-small-letters-test9
  (not (valid-inputs? "" "sasddasdaadsadsda")))

(deftest check-if-small-letters-test10
  (not (valid-inputs? "asdasdasdasdasdasdsadas" "")))