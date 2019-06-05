class MarsRover(var _position: Position, var _direction: Direction.Value, var _grid: Grid) {

    // Command characters
    private val Forward = 'f'
    private val Backward = 'b'
    private val Left = 'l'
    private val Right = 'r'

    // Parsing command list, reacting according to specific command
    // one at a time and changing rover's position or direction
    def command(command: String): Unit = {
        val commands = command.toList
        for (command <- commands) {
            if (List(Forward, Backward).contains(command)) {
                move(command)
            } else if (List(Left, Right).contains(command)) {
                rotate(command)
            }
        }
    }

    // TODO: refactor
    // Moving rover on the grid forwards or backwards
    def move(command: Char): Unit = {
        var x = _position.x
        var y = _position.y
        if ((command == Forward && _direction == Direction.North)
            || (command == Backward && _direction == Direction.South)
        ) {
            y = (y + 1) % _grid.rows
        } else if ((command == Forward && _direction == Direction.South)
            || (command == Backward && _direction == Direction.North)
        ) {
            y = y - 1
            if (y < 0) {
                y = _grid.rows - 1
            }
        }
        if ((command == Forward && _direction == Direction.East)
            || (command == Backward && _direction == Direction.West)
        ) {
            x = (x + 1) % _grid.cols
        } else if ((command == Forward && _direction == Direction.West)
            || (command == Backward && _direction == Direction.East)
        ) {
            x = x - 1
            if (x < 0) {
                x = _grid.cols - 1
            }
        }
        _position = Position(x, y)
    }

    // TODO: refactor
    // Rotating rover left
    def rotateLeft(): Unit = {
        if (_direction == Direction.North) {
            _direction = Direction.West
        } else if (_direction == Direction.East) {
            _direction = Direction.North
        } else if (_direction == Direction.South) {
            _direction = Direction.East
        } else if (_direction == Direction.West) {
            _direction = Direction.South
        }
    }

    // TODO: refactor
    // Rotating rover right
    def rotateRight(): Unit = {
        if (_direction == Direction.North) {
            _direction = Direction.East
        } else if (_direction == Direction.East) {
            _direction = Direction.South
        } else if (_direction == Direction.South) {
            _direction = Direction.West
        } else if (_direction == Direction.West) {
            _direction = Direction.North
        }
    }

    // Rotating rover left or right, changing direction
    def rotate(command: Char): Unit = {
        if (command == Left) {
            rotateLeft()
        } else if (command == Right) {
            rotateRight()
        }
    }

    // Getter for checking rover's position
    def position: Position = _position
}
