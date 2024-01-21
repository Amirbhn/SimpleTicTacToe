fun main() {
  fun printGrid(grid: CharArray) {
    println("---------")
    println("| ${grid[0]} ${grid[1]} ${grid[2]} |")
    println("| ${grid[3]} ${grid[4]} ${grid[5]} |")
    println("| ${grid[6]} ${grid[7]} ${grid[8]} |")
    println("---------")
  }

  fun checkWinner(grid: CharArray, player: Char): Boolean {
    for (i in 0..2) {
      // Check rows and columns
      if ((grid[i] == player && grid[i + 3] == player && grid[i + 6] == player) ||
        (grid[i * 3] == player && grid[i * 3 + 1] == player && grid[i * 3 + 2] == player)
      ) {
        return true
      }
    }

    // Check diagonals
    return (grid[0] == player && grid[4] == player && grid[8] == player) ||
            (grid[2] == player && grid[4] == player && grid[6] == player)
  }

  fun isGridFull(grid: CharArray): Boolean {
    return grid.none { it == ' ' }
  }

  val grid = CharArray(9) { ' ' }
  printGrid(grid)

  var currentPlayer = 'X'

  while (true) {
    print("Enter the coordinates (row column): ")
    val input = readLine()!!.split(" ")

    try {
      val row = input[0].toInt() - 1
      val col = input[1].toInt() - 1

      if (row in 0..2 && col in 0..2 && grid[row * 3 + col] == ' ') {
        grid[row * 3 + col] = currentPlayer
        printGrid(grid)

        if (checkWinner(grid, currentPlayer)) {
          println("$currentPlayer wins")
          break
        } else if (isGridFull(grid)) {
          println("Draw")
          break
        }

        currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
      } else {
        if (row !in 0..2 || col !in 0..2) {
          println("Coordinates should be from 1 to 3!")
        } else {
          println("This cell is occupied! Choose another one!")
        }
      }
    } catch (e: NumberFormatException) {
      println("You should enter numbers!")
    }
  }
}
