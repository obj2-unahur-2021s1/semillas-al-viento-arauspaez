package ar.edu.unahur.obj2.semillasAlViento

class Agricultora(val parcelas: List<Parcela>) {
    fun parcelasSemilleras() = parcelas.filter { it.todasDanSemillas() }
    fun parcelaConMasEspacio() = parcelas.maxByOrNull { it.cantidadMaximaPlantas() - it.plantas.size }
    fun plantarEstrategicamente(planta: Planta) { this.parcelaConMasEspacio()?.plantar(planta) }
}