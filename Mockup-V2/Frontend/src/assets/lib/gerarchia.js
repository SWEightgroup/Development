export const gerarchia = {
  adjective: {
    text: { full: 'adjective', short: 'A' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'O', full: 'Ordinal' },
          { short: 'Q', full: 'qualificative' },
          { short: 'P', full: 'Possesive' }
        ],
        condition: null
      },
      {
        attrName: 'degree',
        choices: [
          { short: 'S', full: 'superlative' },
          { short: '0', full: 'none' }
        ],
        condition: { index: 1, short: 'Q' }
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' },
          { short: 'N', full: 'invariable' }
        ],
        condition: null
      },
      {
        attrName: 'possessorpers',
        choices: [
          { short: '1', full: '1' },
          { short: '2', full: '2' },
          { short: '3', full: '3' }
        ],
        condition: { index: 1, short: 'P' }
      },
      {
        attrName: 'possessornum',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' },
          { short: 'N', full: 'invariable' }
        ],
        condition: { index: 1, short: 'P' }
      }
    ]
  },
  conjunction: {
    text: { full: 'conjunction', short: 'C' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'C', full: 'coordinating' },
          { short: 'S', full: 'subordinating' }
        ],
        condition: null
      }
    ]
  },
  determiner: {
    text: { full: 'determiner', short: 'D' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'A', full: 'article' },
          { short: 'D', full: 'demonstrative' },
          { short: 'E', full: 'exclamative' },
          { short: 'I', full: 'indefinite' },
          { short: 'T', full: 'interrogative' },
          { short: 'N', full: 'numeral' },
          { short: 'P', full: 'possessive' }
        ],
        condition: null
      },
      {
        attrName: 'person',
        choices: [
          { short: '1', full: '1' },
          { short: '2', full: '2' },
          { short: '3', full: '3' }
        ],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' },
          { short: 'N', full: 'invariable' }
        ],
        condition: null
      },
      {
        attrName: 'possessornum',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' }
        ],
        condition: null
      }
    ]
  },
  noun: {
    text: { full: 'noun', short: 'N' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'C', full: 'common' },
          { short: 'P', full: 'proper' }
        ],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' }
        ],
        condition: null
      },
      {
        attrName: 'neclass',
        choices: [
          { short: 'S', full: 'person' },
          { short: 'G', full: 'location' },
          { short: 'O', full: 'oranization' },
          { short: 'V', full: 'other' }
        ],
        condition: null
      },
      {
        attrName: 'nesubclass',
        choices: [{ short: '0', full: 'none' }], // se ho una sola scelta metto automaticamente 0
        condition: null
      },
      {
        attrName: 'degree',
        choices: [
          { short: 'A', full: 'augmentative' },
          { short: 'D', full: 'diminutive' }
        ],
        condition: null
      }
    ]
  },
  verb: {
    text: { full: 'verb', short: 'V' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'M', full: 'main' },
          { short: 'A', full: 'auxiliary' },
          { short: 'S', full: 'semiauxiliary' }
        ],
        condition: null
      },
      {
        attrName: 'mood',
        choices: [
          { short: 'I', full: 'indicative' },
          { short: 'S', full: 'subjunctive' },
          { short: 'M', full: 'imperative' },
          { short: 'P', full: 'pastparticiple' },
          { short: 'G', full: 'gerund' },
          { short: 'N', full: 'infinitive' }
        ],
        condition: null
      },
      {
        attrName: 'tense',
        choices: [
          { short: 'P', full: 'present' },
          { short: 'I', full: 'imperfect' },
          { short: 'F', full: 'future' },
          { short: 'P', full: 'past' },
          { short: 'C', full: 'conditional' }
        ],
        condition: null
      },
      {
        attrName: 'person',
        choices: [
          { short: '1', full: '1' },
          { short: '2', full: '2' },
          { short: '3', full: '3' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' }
        ],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' }
        ],
        condition: null
      }
    ]
  },
  pronoun: {
    text: { full: 'pronoun', short: 'P' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'D', full: 'demonstrative' },
          { short: 'E', full: 'exclamative' },
          { short: 'I', full: 'indefinite' },
          { short: 'T', full: 'interrogative' },
          { short: 'N', full: 'numeral' },
          { short: 'P', full: 'personal' },
          { short: 'R', full: 'relative' }
        ],
        condition: null
      },
      {
        attrName: 'person',
        choices: [
          { short: '1', full: '1' },
          { short: '2', full: '2' },
          { short: '3', full: '3' }
        ],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' },
          { short: 'N', full: 'invariable' }
        ],
        condition: null
      },
      {
        attrName: 'case',
        choices: [
          { short: 'N', full: 'nominative' },
          { short: 'A', full: 'accusative' },
          { short: 'D', full: 'dative' },
          { short: 'O', full: 'oblique' }
        ],
        condition: null
      },
      {
        attrName: 'polite',
        choices: [{ short: 'P', full: 'yes' }, { short: '0', full: 'none' }],
        condition: null
      }
    ]
  },
  adverb: {
    text: { full: 'adverb', short: 'R' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'N', full: 'negative' },
          { short: 'G', full: 'general' }
        ],
        condition: null
      }
    ]
  },
  adposition: {
    text: { full: 'adposition', short: 'S' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'P', full: 'preposition' },
          { short: '0', full: 'none' }
        ],
        condition: null
      },
      {
        attrName: 'contracted',
        choices: [{ short: 'C', full: 'yes' }, { short: '0', full: 'none' }],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'M', full: 'masculine' },
          { short: '0', full: 'none' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: '0', full: 'none' }
        ],
        condition: null
      }
    ]
  },
  number: {
    text: { full: 'number', short: 'Z' },
    attributes: [
      {
        attrName: 'type',
        choices: [
          { short: 'd', full: 'partitive' },
          { short: 'm', full: 'currency' },
          { short: 'p', full: 'ratio' },
          { short: 'u', full: 'unit' }
        ],
        condition: null
      }
    ]
  },
  date: {
    text: { full: 'date', short: 'W' },
    attributes: []
  }
};
