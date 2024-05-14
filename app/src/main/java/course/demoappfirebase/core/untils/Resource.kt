package course.demoappfirebase.core.untils

sealed class Resource {
    object Init: Resource()
    object Success: Resource()
    object Failure: Resource()
    object Loading: Resource()
}