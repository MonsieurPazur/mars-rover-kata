object Main {
    def main(args: Array[String]): Unit = {
        val rover = new MarsRover(Position(0, 0), Direction.North, Grid(10, 10))
        rover.command("ffrff")
        println(rover.position.x, rover.position.y)
    }
}
