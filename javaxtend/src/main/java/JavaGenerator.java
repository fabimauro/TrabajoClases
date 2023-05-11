import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Extension;

import com.google.inject.Inject;

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IFileSystemAccess
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess
import org.eclipse.xtext.generator.OutputConfiguration
import org.eclipse.xtext.generator.URIUtil
import org.eclipse.xtext.xbase.compiler.JvmModelGenerator
import org.eclipse.xtext.xbase.typesystem.references.LightweightTypeReference

// Clase generadora de Java
class JavaGenerator implements IGenerator {

    // Generar Java para el modelo
    def void doGenerate(Resource resource, IFileSystemAccess fsa) {

        // Iterar sobre los objetos Ecore del modelo
        for (obj : resource.contents) {

            // Generar Java solo para objetos del tipo Persona
            if (obj instanceof Persona) {

                // Crear un archivo Java para la clase Persona
                val className = obj.name + "Java"
                val javaFile = className + ".java"

                // Escribir la clase Java en el archivo
                fsa.generateFile(javaFile, '''
                public class ''' + className + ''' {
                    private String nombre;
                    public String getNombre() {
                        return nombre;
                    }
                    public void setNombre(String nombre) {
                        this.nombre = nombre;
                    }
                }'''
        )
            }
        }
    }
}

// Transformador de modelo
class ModelTransformer {

    @Inject extension JvmModelGenerator
    @Inject extension LightweightTypeReference

    // Transformar un modelo en Java usando el generador JavaGenerator
    def transform(Resource model) {

        // Generar el modelo Java
        val fsa = new JavaIoFileSystemAccess()
        val outputConfig = new OutputConfiguration("DEFAULT_OUTPUT")
        outputConfig.outputDirectory = "src-gen"
        fsa.setOutputConfigurations(#{ outputConfig })
        fsa.generateFile("test.txt", "This is a test!")
        new JavaGenerator().doGenerate(model, fsa)

        // Compilar el modelo Java
        val javaFiles = fsa.getFiles("src-gen", "*.java")
        JvmModelGenerator.compileJavaFiles(javaFiles, fsa, model)
    }
}

    // Ejemplo de uso
    val transformer = new ModelTransformer()

    // Crear un modelo de prueba con una instancia de Persona
    val model = '''
<?xml version="1.0" encoding="UTF-8"?>
<Model xmlns="http://www.example.com/model" name="TestModel">
<Persona name="Juan" />
</Model>
        '''
        val resource = XtextResourceSet().createResource(URI.createURI("test.model"))
        resource.load(new StringInputStream(model), null)

// Transformar el modelo
        transformer.transform(resource)

