#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME}

#end
import com.kotlindiscord.kord.extensions.extensions.Extension

#parse("File Header.java")
class ${NAME} : Extension() {
    override val name = "${Extension_name}"
    
    override suspend fun setup() {
        TODO("Not yet implemented")
    }
}
