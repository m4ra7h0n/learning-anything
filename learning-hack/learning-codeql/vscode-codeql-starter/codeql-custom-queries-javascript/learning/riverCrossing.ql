import tutorial

// 每次船运输的货物
class Cargo extends string {
  Cargo() { this = ["Nothing", "Goat", "Cabbage", "Wolf"] }
}

/** One of two shores. */
class Shore extends string {
  Shore() { this = ["Left", "Righ"] }

  /** Returns the other shore. */
  Shore other() {
    this = "Left" and result = "Right"
    or
    this = "Right" and result = "Left"
  }
}

/** A record of where everything is. */
class State extends string {
  Shore manShore;
  Shore goatShore;
  Shore cabbageShore;
  Shore wolfShore;

  State() { this = manShore + "," + goatShore + "," + cabbageShore + "," + wolfShore }

  /** Returns the state that is reached after ferrying a particular cargo item. */
  State ferry(Cargo cargo) {
    cargo = "Nothing" and
    result = renderState(manShore.other(), goatShore, cabbageShore, wolfShore)
    or
    cargo = "Goat" and
    result = renderState(manShore.other(), goatShore.other(), cabbageShore, wolfShore)
    or
    cargo = "Cabbage" and
    result = renderState(manShore.other(), goatShore, cabbageShore.other(), wolfShore)
    or
    cargo = "Wolf" and
    result = renderState(manShore.other(), goatShore, cabbageShore, wolfShore.other())
  }

  predicate isSafe() {
    (goatShore != cabbageShore or goatShore = manShore) and
    (wolfShore != goatShore or wolfShore = manShore)
  }

  State safeFerry(Cargo cargo) { result = this.ferry(cargo) and result.isSafe() }

  string towards() {
    manShore = "Left" and result = "to the left"
    or
    manShore = "Right" and result = "to the right"
  }

  /**
   * Returns all states that are reachable via safe ferrying.
   * `path` keeps track of how it is achieved and `steps` keeps track of the number of steps it takes.
   */
  State reachesVia(string path, int steps) {
    // Trivial case: a state is always reachable from itself
    steps = 0 and this = result and path = ""
    or
    // A state is reachable using pathSoFar and then safely ferrying cargo.
    exists(int stepsSoFar, string pathSoFar, Cargo cargo |
      result = this.reachesVia(pathSoFar, stepsSoFar).safeFerry(cargo) and
      steps = stepsSoFar + 1 and
      // We expect a solution in 7 steps, but you can choose any value here.
      steps <= 7 and
      path = pathSoFar + "\n Ferry " + cargo
    )
  }

}

/** The initial state, where everything is on the left shore. */
class InitialState extends State {
  InitialState() { this = "Left" + "," + "Left" + "," + "Left" + "," + "Left" }
}

/** The goal state, where everything is on the right shore. */
class GoalState extends State {
  GoalState() { this = "Right" + "," + "Right" + "," + "Right" + "," + "Right" }
}

/** Renders the state as a string. */
string renderState(Shore manShore, Shore goatShore, Shore cabbageShore, Shore wolfShore) {
  result = manShore + "," + goatShore + "," + cabbageShore + "," + wolfShore
}

from string path
where any(InitialState i).reachesVia(path, _) = any(GoalState g)
select path + "."
