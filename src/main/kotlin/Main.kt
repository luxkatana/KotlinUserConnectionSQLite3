import java.sql.*

fun create_connection(path: String): Connection {
    return DriverManager.getConnection("jdbc:sqlite:$path")
}
fun get_user(name: String, password: String, connection: Connection): User?
{
    val statement = connection.createStatement()
    val result = statement.executeQuery("SELECT * FROM users WHERE name = '$name' AND password = '$password'")
    if (result.next())
        return User(result.getString("name"), result.getString("password"), result.getInt("money"), connection)
    return null

}
fun create_user(name: String, password: String, money: Int=0, connection: Connection): User?
{
    val statement = connection.createStatement()
    try {
        statement.executeQuery("INSERT INTO users VALUES('$name', '$password', $money)")
    } catch (e: SQLException)
    {
        return null
    }
    return User(name, password, money, connection)
}
fun main() {
    val connection: Connection = create_connection("data\\mydb.db")
    println("connected to database -> $connection")
    print("name -> ")
    val name: String = readLine()!!.toString()
    print("password ->")
    val password: String = readLine()!!.toString()
    val user: User? = get_user(name, password, connection)
    when(user)
    {
        null -> {
            println("Incorrect name or password")
            return
        }
        else -> println("Access granted. Welcome ${user.name}!")
    }

    connection.close()
}
