class MarsRover(var _position: Position, direction: Direction.Value, grid: Grid) {
    private var _direction = direction
    private val _grid = grid

    def command(command: String): Unit = {
        val commands = command.toList
        for (command <- commands) {
            if (List('f', 'b').contains(command)) {
                move(command)
            } else if (List('l', 'r').contains(command)) {
                rotate(command)
            }
        }
    }

    def move(command: Char): Unit = {
        var x = _position.x
        var y = _position.y
        if ((command == 'f' && _direction == Direction.North)
            || (command == 'b' && _direction == Direction.South)
        ) {
            y = (y + 1) % _grid.rows
        } else if ((command == 'f' && _direction == Direction.South)
            || (command == 'b' && _direction == Direction.North)
        ) {
            y = y - 1
            if (y < 0) {
                y = _grid.rows - 1
            }
        }
        if ((command == 'f' && _direction == Direction.East)
            || (command == 'b' && _direction == Direction.West)
        ) {
            x = (x + 1) % _grid.cols
        } else if ((command == 'f' && _direction == Direction.West)
            || (command == 'b' && _direction == Direction.East)
        ) {
            x = x - 1
            if (x < 0) {
                x = _grid.cols - 1
            }
        }
        _position = Position(x, y)
    }

    def rotate(command: Char): Unit = {
        if (command == 'l') {
            if (_direction == Direction.North) {
                _direction = Direction.West
            } else if (_direction == Direction.East) {
                _direction = Direction.North
            } else if (_direction == Direction.South) {
                _direction = Direction.East
            } else if (_direction == Direction.West) {
                _direction = Direction.South
            }
        } else if (command == 'r') {
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
    }

    def position: Position = _position
}
