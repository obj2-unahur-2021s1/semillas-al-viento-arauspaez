package ar.edu.unahur.obj2.semillasAlViento

import java.lang.Exception

class Parcela(val ancho: Float, val largo: Float, val horasSolPorDia: Int) {
    var plantas = mutableListOf<Planta>()

    fun superficie() = ancho * largo
    fun cantidadMaximaPlantas() =
        if(ancho > largo) (this.superficie() / 5).toInt()
        else (this.superficie() / 3 + largo).toInt()

    fun tieneComplicaciones() = plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }

    fun alcanzoElLimite() = plantas.size == this.cantidadMaximaPlantas()
    fun seVaAQuemar(planta: Planta) = horasSolPorDia > planta.horasDeSolQueTolera() + 2

    fun plantar(planta: Planta) {
        if ( this.alcanzoElLimite() || this.seVaAQuemar(planta)) {
            throw Exception("Ya no hay lugar en esta parcela o la planta se va a quemar")
        } else { plantas.add(planta)  }
    }
    fun todasDanSemillas() = plantas.all { it.daSemillas() }
}

