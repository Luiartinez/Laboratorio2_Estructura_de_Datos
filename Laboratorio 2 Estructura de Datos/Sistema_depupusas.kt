import java.util.*

data class Pupusa(val tipo: String, val cantidad: Int)
data class Orden(val cliente: String, val pupusas: List<Pupusa>)

val ordenesPendientes: Queue<Orden> = LinkedList()
val ordenesDespachadas: Stack<Orden> = Stack()

fun main() {
    while (true) {
        println("\nBienvenido a la pupuseria el comalito")
        println("\nPor favor seleccione una opcion: ")
        println("\n1. Registrar orden\n2. Ver pendientes\n3. Despachar\n4. Ver despachadas\n5. Salir")
        when (readln().toIntOrNull()) {
            1 -> registrarOrden()
            2 -> mostrarOrdenes(ordenesPendientes, "Órdenes pendientes")
            3 -> despacharOrden()
            4 -> mostrarOrdenes(ordenesDespachadas, "Órdenes despachadas")
            5 -> break
            else -> println("Opción inválida.")
        }
    }
}

fun registrarOrden() {
    print("\nNombre del cliente: "); val nombre = readln()
    print("¿Cuántos tipos de pupusas?: "); val numTipos = readln().toIntOrNull() ?: 0
    val pupusas = List(numTipos) {
        print("Tipo de Pupusas: "); val tipo = readln()
        print("Cantidad de pupusas: "); val cant = readln().toIntOrNull() ?: 0
        Pupusa(tipo, cant)
    }
    ordenesPendientes.add(Orden(nombre, pupusas))
    println("Orden registrada.")
}

fun mostrarOrdenes(ordenes: Collection<Orden>, titulo: String) {
    if (ordenes.isEmpty()) println("No hay $titulo.")
    else ordenes.forEachIndexed { i, o -> println("${i + 1}. ${o.cliente}: ${o.pupusas.joinToString(", ") { "${it.cantidad} de ${it.tipo}" }}") }
}

fun despacharOrden() {
    if (ordenesPendientes.isEmpty()) println("No hay órdenes para despachar.")
    else ordenesDespachadas.push(ordenesPendientes.poll()).also { println("Orden de ${it.cliente} despachada.") }
}
