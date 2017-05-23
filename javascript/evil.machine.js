const Word = require('./word')

class EvilMachine {
    constructor(substitutions = {}) {
        this.substitutions = substitutions
    }

    censor(text) {
        return text
            .split(' ')
            .map(s => new Word(s))
            .map(w => w.censor(this.substitutions))
            .map(w => w.toString())
            .join(' ')
    }
}

module.exports = EvilMachine