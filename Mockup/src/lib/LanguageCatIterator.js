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
    this.langStruct = langStruct;
    this.setBaseLevel();
  }

  /**
   * Makes iterator return to the base of the language gerarchy.
   */
  setBaseLevel() {
    this.solution = []; // array di oggetti
    this.currentLevel = -1;
    this.categoryData = null;
    this.categoryText = '';
    this.currentButtonList = Object.values(this.langStruct.gerarchy);
  }

  /**
   * Solution given by the user so far.
   *
   * The solution is returned as a code if verbose is FALSE, otherwise it will be return in a human readable form.
   *
   * @param {boolean} verbose Specifies whether the solution must be returned as full words or as an abbreviation.
   * @return Solution given by the user so far.
   */
  getSolution(verbose = true) {
    console.log('solution', this.solution);
    if (!verbose) {
      return this.solution.map(element => element.data[1]).join(' ');
    }
    return this.solution.map(element => element.data[0]).join(' ');
  }

  getSolutionComplete() {
    const sol = this.solution;
    const arr = Object.assign(
      {},
      ...sol.map(item => ({ [item.text]: item.data }))
    );
    return arr;
  }

  /**
   * Function to return the buttons to show in the main interface
   *
   * @return Array in which each string is the label of a language category button.
   */
  getCurrentButtonList() {
    if (this.currentLevel === -1) {
      return this.currentButtonList.map(item => item.text[0]);
    }
    if (this.currentButtonList.data)
      return this.currentButtonList.data.map(item => item[0]);
    return [];
  }

  /**
   * Makes iterator advance to the next level in the gerarchy.
   *
   * The iterator advances the to next level and stores the choice made by the user.
   *
   * @param {string} choice Choice made by the user on between the attribute options.
   */
  nextLevel(choice) {
    let element = null;
    let category = null;

    if (this.currentLevel === -1) {
      category = this.currentButtonList.find(item => item.text[0] === choice);
      if (!category) throw new RangeError('Element not found');
      this.categoryData = Object.values(category.data);
      this.categoryText = category.text;
      element = { text: 'pos', data: this.categoryText };
    } else if (this.currentLevel > this.categoryData.length - 1) {
      this.currentButtonList = [];
    } else {
      element = {
        text: this.currentButtonList.text,
        data: this.currentButtonList.data.find(item => item[0] === choice)
      };
      if (!element) throw new RangeError('Element not found');
    }
    if (this.currentLevel < this.categoryData.length - 1) {
      this.currentLevel += 1;
      // Adding current choices to the button list
      this.currentButtonList = this.categoryData[this.currentLevel];
      this.solution.push(element);
    } else if (this.currentLevel === this.categoryData.length - 1) {
      this.solution.push(element);
      this.currentButtonList = [];
    }
  }

  /**
   * Makes iterator go back one choice.
   *
   * The iterator goes back by one choice and forgets the last solutions given by the user. If it is already at base level,
   * it does nothing.
   */
  prevLevel() {
    // If we already are at the base level, we do nothing. Can't go back
    if (this.currentLevel === 0) {
      this.setBaseLevel();
    } else if (this.currentLevel !== -1) {
      this.currentLevel -= 1;
      this.currentButtonList = this.categoryData[this.currentLevel];
      this.solution.pop();
    }
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
