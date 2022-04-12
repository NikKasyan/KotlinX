package at.kasyan.ext.args

import java.lang.RuntimeException

class ArgumentParser private constructor(private val options: Array<out Option>) {

    companion object {
        fun create(vararg options: Option): ArgumentParser {
            return ArgumentParser(options)
        }
    }

    fun parse(arguments: Iterable<String>) {
        val iterator = arguments.iterator()
        val args = Arguments()
        while (iterator.hasNext()) {
            val current = iterator.next()
            val option = this.options.find { it.longOption == current || it.shortOption == current }
                ?: throw OptionNotFoundException(current)
            if (option.hasArgs) {
                val value = if(iterator.hasNext()) iterator.next() else throw RuntimeException("Missing argument for option $current")
                args.addArgument(option, value)
            }else {
                args.addArgument(option)
            }

        }
    }

}

class OptionNotFoundException(optionIdentifier: String) :
    RuntimeException("Option with name $optionIdentifier not found") {

}

data class Option(
    val longOption: String,
    val shortOption: String = "",
    val description: String = "",
    val hasArgs: Boolean = false
)

data class Argument(val option: Option, val value: String = "")



class Arguments {
    fun addArgument(option: Option) {
        TODO("Not yet implemented")
    }

    fun addArgument(option: Option, value: String) {
        TODO("Not yet implemented")
    }

}