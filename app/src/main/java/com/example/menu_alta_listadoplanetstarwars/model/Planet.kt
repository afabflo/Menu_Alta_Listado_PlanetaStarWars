
data class Planet(
    val id: Int,
    var name: String,
    var rotation_period: String,
    var orbital_period: String,
    var diameter: String,
    var climate: String,
    var gravity: String,
    var terrain: String,
    var surface_water: String,
    var population: String,
    var residents: List<String> = emptyList(),
    var films: List<String> = emptyList()
)
