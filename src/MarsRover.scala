class MarsRover(var _position: Position, var _direction: Direction.Value, var _grid: Grid) {

    // Command characters
    private val Forward = 'f'
    private val Backward = 'b'
    private val Left = 'l'
    private val Right = 'r'

    // Storing easily accessed directions
    // (so we can increment/decrement current)
    private val _directions = Map(
        0 -> Direction.North,
        1 -> Direction.East,
        2 -> Direction.South,
        3 -> Direction.West
    )

    // By default we set first value from map
    // Current direction points to _directions map
    private var _currentDirection = 0

    // Then we look in directions map and search for given _direction
    for ((key, value) <- _directions) {
        if (_direction == value) {
            _currentDirection = key
        }
    }

    // Parsing command list, reacting according to specific command
    // one at a time and changing rover's position or direction
    def command(command: String): Unit = {
        val commands = command.toList
        for (command <- commands) {
            _dispatchCommand(command)
        }
    }

    // Getter for checking rover's position
    def position: Position = _position

    // Fixes position (so it's not out of bounds)
    def _getFixedPosition(x: Int, y: Int): Position = {
        var fixedX = x
        if (x < 0) {
            fixedX = _grid.cols - 1
        }
        fixedX = fixedX % _grid.cols
        var fixedY = y
        if (y < 0) {
            fixedY = _grid.rows - 1
        }
        fixedY = fixedY % _grid.rows
        Position(fixedX, fixedY)
    }

    // Dispatches command to specific method
    def _dispatchCommand(command: Char): Unit = {
        command match {
            case Forward => _moveForward()
            case Backward => _moveBackward()
            case Left => _rotateLeft()
            case Right => _rotateRight()
        }
    }

    // Moves rover forwards (relative to direction)
    def _moveForward(): Unit = {
        var x = _position.x
        var y = _position.y
        _direction match {
            case Direction.North => y += 1
            case Direction.East => x += 1
            case Direction.South => y -= 1
            case Direction.West => x -= 1
        }
        _position = _getFixedPosition(x, y)
    }

    // Moves rover backwards (relative to direction)
    def _moveBackward(): Unit = {
        var x = _position.x
        var y = _position.y
        _direction match {
            case Direction.North => y -= 1
            case Direction.East => x -= 1
            case Direction.South => y += 1
            case Direction.West => x += 1
        }
        _position = _getFixedPosition(x, y)
    }

    // Rotating rover left
    def _rotateLeft(): Unit = {
        _currentDirection = _currentDirection - 1
        if (_currentDirection < 0) {
            _currentDirection = _directions.size
        }
        _direction = _directions(_currentDirection)
    }

    // Rotating rover right
    def _rotateRight(): Unit = {
        _currentDirection = _currentDirection + 1
        if (_currentDirection > _directions.size) {
            _currentDirection = 0
        }
        _direction = _directions(_currentDirection)
    }
}
