const gerarchia = {
  cat_adjective: {
    text: { full: 'cat_adjective', short: 'A' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'O', full: 'choice_ordinal' },
          { short: 'Q', full: 'choice_quali' },
          { short: 'P', full: 'choice_poss' }
        ],
        condition: null
      },
      {
        attrName: 'attr_degree',
        choices: [
          { short: 'S', full: 'choice_super' },
          { short: '0', full: 'choice_none' }
        ],
        condition: { index: 1, short: 'Q' }
      },
      {
        attrName: 'attr_gen',
        choices: [
          { short: 'F', full: 'choice_feminile' },
          { short: 'M', full: 'choice_masculine' },
          { short: 'C', full: 'choice_common' },
          { short: 'N', full: 'choice_neuter' }
        ],
        condition: null
      },

      {
        attrName: 'attr_num',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' },
          { short: 'N', full: 'choice_invariable' }
        ],
        condition: null
      },
      {
        attrName: 'attr_possessorpers',
        choices: [
          { short: '1', full: 'choice_1pers' },
          { short: '2', full: 'choice_2pers' },
          { short: '3', full: 'choice_3pers' }
        ],
        condition: { index: 1, short: 'P' }
      },
      {
        attrName: 'attr_possessornum',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' },
          { short: 'N', full: 'choice_invariable' }
        ],
        condition: { index: 1, short: 'P' }
      }
    ]
  },
  cat_conjunction: {
    text: { full: 'cat_conjunction', short: 'C' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'C', full: 'choice_coordinating' },
          { short: 'S', full: 'choice_subordinating' }
        ],
        condition: null
      }
    ]
  },
  cat_determiner: {
    text: { full: 'cat_determiner', short: 'D' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'A', full: 'choice_article' },
          { short: 'D', full: 'choice_demonstrative' },
          { short: 'E', full: 'choice_exclamative' },
          { short: 'I', full: 'choice_indefinite' },
          { short: 'T', full: 'choice_interrogative' },
          { short: 'N', full: 'choice_numeral' },
          { short: 'P', full: 'choice_possessive' }
        ],
        condition: null
      },
      {
        attrName: 'attr_person',
        choices: [
          { short: '1', full: 'choice_1pers' },
          { short: '2', full: 'choice_2pers' },
          { short: '3', full: 'choice_3pers' }
        ],
        condition: { index: 1, short: 'P' }
      },
      {
        attrName: 'attr_gen',
        choices: [
          { short: 'F', full: 'choice_feminile' },
          { short: 'M', full: 'choice_masculine' },
          { short: 'C', full: 'choice_common' },
          { short: 'N', full: 'choice_neuter' }
        ],
        condition: null
      },
      {
        attrName: 'attr_num',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' },
          { short: 'N', full: 'choice_invariable' }
        ],
        condition: null
      },
      {
        attrName: 'attr_possessornum',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' }
        ],
        condition: { index: 1, short: 'P' }
      }
    ]
  },
  cat_noun: {
    text: { full: 'cat_noun', short: 'N' },
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
          { short: 'P', full: 'plural' },
          { short: 'N', full: 'invariable' }
        ],
        condition: null
      },
      {
        attrName: 'neclass',
        choices: [
          { short: 'S', full: 'person' },
          { short: 'G', full: 'location' },
          { short: 'O', full: 'oranization' },
          { short: 'V', full: 'other' },
          { short: '0', full: 'none' }
        ],
        condition: null
      },
      {
        attrName: 'nesubclass',
        choices: [{ short: '0', full: 'none' }], // se ho una sola scelta metto automaticamente 0
        condition: { index: 1, short: 'void' } // farlocco
      },
      {
        attrName: 'degree',
        choices: [
          { short: 'A', full: 'augmentative' },
          { short: 'D', full: 'diminutive' },
          { short: '0', full: 'none' }
        ],
        condition: null
      }
    ]
  },
  cat_verb: {
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
          { short: '3', full: '3' },
          { short: '0', full: 'None' }
        ],
        condition: null
      },
      {
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
          { short: 'P', full: 'plural' },
          { short: '0', full: 'None' }
        ],
        condition: null
      },
      {
        attrName: 'gen',
        choices: [
          { short: 'F', full: 'feminile' },
          { short: 'M', full: 'masculine' },
          { short: 'C', full: 'common' },
          { short: 'N', full: 'neuter' },
          { short: '0', full: 'none' },
          { short: '0', full: 'None' }
        ],
        condition: null
      }
    ]
  },
  cat_pronoun: {
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
        condition: { index: 1, full: 'P' }
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
          { short: 'O', full: 'oblique' },
          { short: '0', full: 'None' }
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
  cat_adverb: {
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
  cat_adposition: {
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
        attrName: 'num',
        choices: [
          { short: 'S', full: 'singular' },
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
      }
    ]
  },
  cat_number: {
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
  cat_date: {
    text: { full: 'date', short: 'W' },
    attributes: []
  }
};
export default gerarchia;
