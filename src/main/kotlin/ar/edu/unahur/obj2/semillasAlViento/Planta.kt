package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, val altura: Float) { //SIMPLICIDAD val altura- La altura NUNCA cambiara.
    fun esFuerte() = this.horasDeSolQueTolera() > 10
    abstract fun condicionAlternativa(): Boolean // para evaluar por un lado si esFuerte() y por otro la condicionAlternativa()
    abstract fun horasDeSolQueTolera(): Int
    abstract fun daSemillas(): Boolean
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
    override fun horasDeSolQueTolera() = 6
    override fun condicionAlternativa() = altura > 0.4 // Condicion alternativa a la funcion 'esFuerte'
    override fun daSemillas() = this.esFuerte() || this.condicionAlternativa()
}

open class Soja(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
    override fun horasDeSolQueTolera(): Int {
        return when {
            altura < 0.5 -> 6
            altura < 1 -> 7
            else -> 9
        }
    }
    override fun condicionAlternativa() = anioObtencionSemilla > 2007 && altura > 1
    override fun daSemillas(): Boolean = this.esFuerte() || this.condicionAlternativa()
}

class SojaTransgenica(anioObtencionSemilla: Int,altura: Float): Soja(anioObtencionSemilla,altura){
    override fun horasDeSolQueTolera() = super.horasDeSolQueTolera() * 2
    override fun daSemillas() = false
}