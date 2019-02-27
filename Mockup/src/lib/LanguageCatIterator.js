class LanguageIterator {
  /**
   * Levels explanation:
   * -1 =  Base level (adjective, verb, ecc)
   * > -1 = internal levels
   *
   */

  constructor(langStruct) {
    this.langStruct = langStruct;
    this.setBaseLevel();
  }

  setBaseLevel() {
    this.solution = []; // array di oggetti
    this.currentLevel = -1;
    this.categoryData = null;
    this.categoryText = '';
    this.currentButtonList = Object.values(this.langStruct.gerarchy);
  }

  getSolution(verbose = true) {
    if (!verbose) {
      return this.solution.map(element => element[1]).join('');
    }
    return this.solution.map(element => element[0]).join(' ');
  }

  getCurrentButtonList() {
    if (this.currentLevel === -1) {
      return this.currentButtonList.map(item => item.text[0]);
    }
    return this.currentButtonList.map(item => item[0]);
  }

  nextLevel(choice) {
    let element = null;
    let category = null;

    if (this.currentLevel === -1) {
      category = this.currentButtonList.find(item => item.text[0] === choice);
      if (!category) throw new RangeError('Element not found');
      this.categoryData = Object.values(category.data);
      this.categoryText = category.text;
      element = this.categoryText;
    } else if (this.currentLevel > this.categoryData.length - 1) {
      this.currentButtonList = [];
    } else {
      element = this.currentButtonList.find(item => item[0] === choice);
      if (!element) throw new RangeError('Element not found');
    }
    if (this.currentLevel < this.categoryData.length - 1) {
      this.currentLevel += 1;
      this.currentButtonList = this.categoryData[this.currentLevel];
      this.solution.push(element);
    } else if (this.currentLevel === this.categoryData.length - 1) {
      this.solution.push(element);
      this.currentButtonList = [];
    }
  }

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

class LanguageStructure {
  /*
   * ger -> internal json language gerarchy representation
   */

  /**
   *
   * @param {file} json_source Source json file for language tagging
   */
  constructor(jsonSource) {
    this.gerarchy = jsonSource;
  }

  getBaseIterator() {
    return new LanguageIterator(this);
  }
}

export default LanguageStructure;
