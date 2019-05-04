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
        attrName: 'attr_type',
        choices: [
          { short: 'C', full: 'choice_common' },
          { short: 'P', full: 'choice_proper' }
        ],
        condition: null
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
        attrName: 'num',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' },
          { short: 'N', full: 'choice_invariable' }
        ],
        condition: null
      },
      {
        attrName: 'attr_neclass',
        choices: [
          { short: 'S', full: 'choice_person' },
          { short: 'G', full: 'choice_location' },
          { short: 'O', full: 'choice_organization' },
          { short: 'V', full: 'choice_other' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_nesubclass',
        choices: [{ short: '0', full: 'choice_none' }], // se ho una sola scelta metto automaticamente 0
        condition: { index: 1, short: 'void' } // farlocco
      },
      {
        attrName: 'attr_degree',
        choices: [
          { short: 'A', full: 'choice_augmentative' },
          { short: 'D', full: 'choice_diminutive' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      }
    ]
  },
  cat_verb: {
    text: { full: 'cat_verb', short: 'V' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'M', full: 'choice_main' },
          { short: 'A', full: 'choice_auxiliary' },
          { short: 'S', full: 'choice_semiauxiliary' }
        ],
        condition: null
      },
      {
        attrName: 'attr_mood',
        choices: [
          { short: 'I', full: 'choice_indicative' },
          { short: 'S', full: 'choice_subjunctive' },
          { short: 'M', full: 'choice_imperative' },
          { short: 'P', full: 'choice_pastparticiple' },
          { short: 'G', full: 'choice_gerund' },
          { short: 'N', full: 'choice_infinitive' }
        ],
        condition: null
      },
      {
        attrName: 'attr_tense',
        choices: [
          { short: 'P', full: 'choice_present' },
          { short: 'I', full: 'choice_imperfect' },
          { short: 'F', full: 'choice_future' },
          { short: 'P', full: 'choice_past' },
          { short: 'C', full: 'choice_conditional' }
        ],
        condition: null
      },
      {
        attrName: 'attr_person',
        choices: [
          { short: '1', full: 'choice_1pers' },
          { short: '2', full: 'choice_2pers' },
          { short: '3', full: 'choice_3pers' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_num',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: 'P', full: 'choice_plural' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_gen',
        choices: [
          { short: 'F', full: 'choice_feminile' },
          { short: 'M', full: 'choice_masculine' },
          { short: 'C', full: 'choice_common' },
          { short: 'N', full: 'choice_neuter' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      }
    ]
  },
  cat_pronoun: {
    text: { full: 'cat_pronoun', short: 'P' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'D', full: 'choice_demonstrative' },
          { short: 'E', full: 'choice_exclamative' },
          { short: 'I', full: 'choice_indefinite' },
          { short: 'T', full: 'choice_interrogative' },
          { short: 'N', full: 'choice_numeral' },
          { short: 'P', full: 'choice_personal' },
          { short: 'R', full: 'choice_relative' }
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
        condition: { index: 1, full: 'P' }
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
        attrName: 'attr_case',
        choices: [
          { short: 'N', full: 'choice_nominative' },
          { short: 'A', full: 'choice_accusative' },
          { short: 'D', full: 'choice_dative' },
          { short: 'O', full: 'choice_oblique' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_polite',
        choices: [
          { short: 'P', full: 'choice_polite' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      }
    ]
  },
  cat_adverb: {
    text: { full: 'cat_adverb', short: 'R' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'N', full: 'choice_negative' },
          { short: 'G', full: 'choice_general' }
        ],
        condition: null
      }
    ]
  },
  cat_adposition: {
    text: { full: 'cat_adposition', short: 'S' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'P', full: 'choice_preposition' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_num',
        choices: [
          { short: 'S', full: 'choice_singular' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_contracted',
        choices: [
          { short: 'C', full: 'choice_contracted' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      },
      {
        attrName: 'attr_gen',
        choices: [
          { short: 'M', full: 'choice_masculine' },
          { short: '0', full: 'choice_none' }
        ],
        condition: null
      }
    ]
  },
  cat_number: {
    text: { full: 'cat_number', short: 'Z' },
    attributes: [
      {
        attrName: 'attr_type',
        choices: [
          { short: 'd', full: 'choice_partitive' },
          { short: 'm', full: 'choice_currency' },
          { short: 'p', full: 'choice_ratio' },
          { short: 'u', full: 'choice_unit' }
        ],
        condition: null
      }
    ]
  },
  cat_date: {
    text: { full: 'cat_date', short: 'W' },
    attributes: []
  }
};
export default gerarchia;
