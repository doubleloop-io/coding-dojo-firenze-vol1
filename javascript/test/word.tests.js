/*global test*/

const assert = require('assert')
const Word = require('../word')

test('string conversion', () => {
    const value = 'any'

    var word = new Word(value)

    assert.equal(word.toString(), value)
})

test('equality', () => {
    var word = new Word('nice')
    var same = new Word('nice')
    var different = new Word('ugly')

    assert(word.equals(same))
    assert(!word.equals(different))
})

test('censor word', () => {
    const word = new Word('nice')

    const result = word.censor({'nice': ':X'})

    assert.equal(result, 'XXXX')
})

test('configurable censor symbol', () => {
    const word = new Word('nice')

    const result = word.censor({'nice': ':Y'})

    assert.equal(result, 'YYYY')
})

test('nothing to censor', () => {
    const word = new Word('ugly')

    const result = word.censor({'nice': ':X'})

    assert.equal(result, 'ugly')
})

test('partial match', () => {
    const word = new Word('friendly')

    const result = word.censor({'friend': ':X'})

    assert.equal(result, 'XXXXXXXX')
})

test('substitute word', () => {
    const word = new Word('bad')

    const result = word.censor({'bad': 'ungood'})

    assert.equal(result, 'ungood')
})

test('nothing to substitute', () => {
    const word = new Word('worse')

    const result = word.censor({'bad': 'ungood'})

    assert.equal(result, 'worse')
})