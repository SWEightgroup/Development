class SolutionMapper {
  translation = [];

  constructor(solutionString, langStruct) {
    const trad = [];
    const arrayCode = solutionString.split('');
    const langStructArray = Object.values(langStruct);

    const primoLivello = langStructArray.find(
      item => item.text.short === arrayCode[0]
    );
    trad.push(primoLivello.text);

    for (let i = 0; i < primoLivello.attributes.length; i += 1) {
      if (arrayCode[i + 1] !== '0') {
        const element = primoLivello.attributes[i].choices.find(
          choice => choice.short === arrayCode[i + 1]
        );
        trad.push(element);
      } else {
        trad.push({ short: 0, full: '' });
      }
    }
    this.translation = trad;
  }

  getSolution() {
    return this.translation;
  }

  getVerboseSolution() {
    console.log(
      ': SolutionMapper -> getVerboseSolution -> this.translation',
      this.translation
    );
    return this.translation
      .map(element => {
        console.log('aaaaaaaaaaaaaaaaaaaaa', element);
        return element.full;
      })
      .join(' ');
  }

  getCodeSolution() {
    return this.translation.map(element => element.short).join('');
  }
}

export default SolutionMapper;
