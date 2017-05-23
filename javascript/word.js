module.exports = class Word {
    constructor(value) {
        this.value = value
    }

    censor(blackwords) {
        const result = this.findSubstitution(this.value, blackwords)
        if (!result) return this

        if (this.shouldObfuscate(result))
            return this.obfuscate(result[1])
        else
            return new Word(result)
    }

    findSubstitution(value, blackwords) {
        const wordMatch = blackword => value.toLowerCase().includes(blackword)

        const keys = Object.keys(blackwords)
        const values = keys.map(k => blackwords[k])

        const index = keys.findIndex(wordMatch)
        return values[index]
    }

    shouldObfuscate(result) {
        return result[0] === ':'
    }
    
    obfuscate(symbol) {
        return new Word(symbol.repeat(this.value.length))
    }

    equals(other) {
        return this.toString() === other.toString()
    }

    toString() {
        return this.value
    }
}