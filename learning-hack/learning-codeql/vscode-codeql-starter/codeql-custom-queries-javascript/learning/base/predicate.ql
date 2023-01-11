import javascript
import tutorial

// define a predicate
predicate isCountry(string country) {
  country = "Germany"
  or
  country = "Belgium"
  or
  country = "France"
}

predicate hasCapital(string country, string capital) {
  country = "Belgium" and capital = "Brussels"
  or
  country = "Germany" and capital = "Berlin"
  or
  country = "France" and capital = "Paris"
}

// predicate without result
predicate isSmall(int i) {
  i in [1 .. 9]
}

// predicate with result
int getSuccessor(int i) {
  result = i + 1 and
  i in [1 .. 9]
}

// Person getAChildOf(Person p) {
//   p = getAParentOf(result)
// }

string getANeighbor(string country) {
  country = "France" and result = "Belgium"
  or
  country = "France" and result = "Germany"
  or
  country = "Germany" and result = "Austria"
  or
  country = "Germany" and result = "Belgium"
}

string getANeighborRec(string country) {
  country = "France" and result = "Belgium"
  or
  country = "France" and result = "Germany"
  or
  country = "Germany" and result = "Austria"
  or
  country = "Germany" and result = "Belgium"
  or
  country = getANeighbor(result)
}

from string s
where s = "Belgium"
select getANeighborRec(s)
