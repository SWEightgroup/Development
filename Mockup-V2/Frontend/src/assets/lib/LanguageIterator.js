/**
 * @classdesc Iterator class for a LanguageStructure.
 *
 *
 * Levels explanation:
 * -1 =  Base level (adjective, verb, ecc)
 * > -1 = internal levels.
 *
 *
 */

class LanguageIterator {
  /**
   * Sole constructuro of this class
   *
   * @param {LanguageStructure} langStruct Language data structure on which the iterator works.
   */
  constructor(langStruct) {
    this.langStruct = langStruct.gerarchy;
    this.setBaseLevel();
  }

  /**
   * Makes iterator return to the base of the language gerarchy.
   */
  setBaseLevel() {
    this.level = -1;
    this.category = null;
    this.solution = new Array();
    this.currentChoices = this.getBaseCategories();
  }

  getBaseCategories() {
    return Object.values(this.langStruct).map(category => category.text);
  }

  getSolution() {
    return this.solution;
  }

  getVerboseSolution() {
    return this.solution.map(solutionItem => solutionItem.full).join(' ');
  }

  /**
   * Function to return the buttons to show in the main interface
   *
   * @return Array in which each string is the label of a language category button.
   */
  getCurrentButtonList() {
    return this.currentChoices;
  }

  /**
   * Makes iterator advance to the next level in the gerarchy.
   *
   * The iterator advances the to next level and stores the choice made by the user.
   *
   * @param {string} choice Choice made by the user on between the attribute options.
   */
  nextLevel(choice) {
    if (this.level === -1) this.category = choice.full;
    if (this.level < this.categoryLength()) {
      this.level++;
      this.solution.push(choice);
      this.currentChoices = this.getCurrentChoices();
    } else {
      this.currentChoices = new Array();
    }
    if (!this.checkCondition()) this.nextLevel({ short: 0, full: '' });
    console.log(this.checkCondition());
  }

  getCurrentChoices() {
    if (this.level < this.categoryLength())
      return this.langStruct[this.category].attributes[this.level].choices;
    else return new Array();
  }

  categoryLength() {
    return this.langStruct[this.category].attributes.length;
  }

  /**
   * Makes iterator go back one choice.
   *
   * The iterator goes back by one choice and forgets the last solutions given by the user. If it is already at base level,
   * it does nothing.
   */
  prevLevel() {
    if (this.level > -1) {
      if (this.level === 0) {
        this.setBaseLevel();
      } else {
        this.level--;
        this.solution.pop();
        this.currentChoices = this.getCurrentChoices();
      }
    }
  }

  checkCondition() {
    if (this.level === this.categoryLength()) return true;
    const categoryAttr = this.langStruct[this.category].attributes;
    if (categoryAttr[this.level].condition === null) return true;
    const { index, short } = categoryAttr[this.level].condition;

    if (this.getSolution()[index].short === short) return true;
    return false;
  }
}

/**
 * @classdesc Representation of the language structure of a certain language. An instance of this class is basically a wrapper around a Json language structure.
 */
class LanguageStructure {
  /**
   * Only constructor of this class.
   *
   * @param {file} json_source Source json file path for language tagging
   */
  constructor(jsonSource) {
    this.gerarchy = jsonSource;
  }

  /**
   * @return A LanguageIterator object to iterate in the LanguageStructure
   */
  getBaseIterator() {
    return new LanguageIterator(this);
  }
}

export default LanguageStructure;
