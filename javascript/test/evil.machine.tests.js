/*global test*/

const assert = require('assert')
const EvilMachine = require('../evil.machine')

test('censor more than one word', () => {
    const evil = new EvilMachine({'nice': ':X', 'ponies': ':X'})

    const result = evil.censor('You have a lot of nice ponies')

    assert.equal(result, 'You have a lot of XXXX XXXXXX')
})

test('sustitute more than one word', () => {
    const evil = new EvilMachine({'bad': 'ungood'})

    const result = evil.censor('sometimes bad things happen')

    assert.equal(result, 'sometimes ungood things happen')
})