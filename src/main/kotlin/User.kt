import java.sql.Connection
class User(_name: String,  _password: String,  _money: Int, _connection: Connection){
    private var connection: Connection = _connection
    var name: String = _name
        set(newvalue)
        {
            field = newvalue
            val statement = connection.createStatement()
            statement.executeUpdate("UPDATE users SET name = '$newvalue' WHERE name = '$name'")
        }

    var password: String = _password
        set(newvalue)
        {
            field = newvalue
            val statement = connection.createStatement()
            statement.executeUpdate("UPDATE users SET password = '$newvalue' WHERE name = '$name'")
        }

    var money: Int = _money
        set(newvalue)
        {
            field = newvalue
            val statement = connection.createStatement()
            statement.executeUpdate("UPDATE users SET money = $newvalue WHERE name = '$name'")
        }
    override fun toString(): String
    {
        return "User(name=$name, password=$password, money=$money)"
    }
}