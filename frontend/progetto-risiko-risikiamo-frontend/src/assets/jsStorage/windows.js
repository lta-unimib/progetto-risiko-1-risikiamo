function impossibleTrade() {
    const instructions = "you can't move troops from the first country you click to the second one you click.";
    const errorWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    errorWindow.document.write(instructions);
    setTimeout(() => {
        errorWindow.window.close();
        //console.log("closed");
    }, 5000);
}

function actionWindow(path) {
    const instructions = '<p>scegli se posizionare i rinforzi (obbligatorio se non si sono posizionati tutti), attaccare o spostarsi</p><div><input type="radio" id="place" name="action" value="place"><label for="place">Piazza</label></div><div><input type="radio" id="attack" name="action" value="attack"><label for="attack">Attacca</label></div><div><input type="radio" id="move" name="action" value="move"><label for="move">Sposta</label></div><p>Inserisci nel caso di spostamento o attacco il territorio di destinazione</p><input type="text" id="destination"><p>inserisci il numero di truppe da spostare o attaccare o rifornire</p><input type="number" id="number" min="1" ><button id="doAction">ok</button><button id="exit">close</button>';
    const actionWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    actionWindow.document.write(instructions);
    const actionButton = actionWindow.document.getElementById('doAction');
    if (actionWindow.document.getElementById('place').checked) {
        actionButton.addEventListener('click', () => this.place(actionWindow.document.getElementById('number').value, path));
        console.log("place");
    }
    else if (actionWindow.document.getElementById('attack').checked) {
        actionButton.addEventListener('click', () => this.attack(actionWindow.document.getElementById('number').value, path, actionWindow.document.getElementById('destination').value));
        console.log("attack");
    }
    else if (actionWindow.document.getElementById('move').checked) {
        actionButton.addEventListener('click', () => this.move(actionWindow.document.getElementById('number').value, path, actionWindow.document.getElementById('destination').value));
        console.log("move");
    }
    const exitButton = actionWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closeActionWindow(path, actionWindow));

}

function closeActionWindow(path, actionWindow) {
    actionWindow.window.close();
    path.classList.remove("pathFrom");
    path.classList.remove("pathTo");
    path.classList.remove("AdjacentPath");
    path.classList.remove("SelectedPath");
}


function openPlaceWindow(path) {
    const instructions = '<p>you can place from 1 to every reinforcement you have on the territory you choose.</p><input type="number" id="number" min="1" ><button id="button">place</button><button id="exit">close</button>';
    const placeWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    placeWindow.document.write(instructions);
    const placeButton = placeWindow.document.getElementById('button');
    placeButton.addEventListener('click', () => this.place(placeWindow.document.getElementById('number').value, path));
    const exitButton = placeWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closePlaceWindow(path, placeWindow));
}

function openTradeWindow(path1, path2) {
    const instructions = '<p>you can move troops from the first country you click to the second one you click.</p><p>you can move up to 3 troops from the first country to the second one.</p><input type="number" id="number" min="1" max="3"><button id="button">trade</button><button id="exit">close</button>';
    const tradeWindow = window.open('', 'Trade Instructions', 'height=300,width=600');
    tradeWindow.document.write(instructions);
    const tradeButton = tradeWindow.document.getElementById('button');
    tradeButton.addEventListener('click', () => this.move(tradeWindow.document.getElementById('number').value, path1, path2));
    const exitButton = tradeWindow.document.getElementById('exit');
    exitButton.addEventListener('click', () => this.closeTradeWindow(path1, path2, tradeWindow));

}

function closeTradeWindow(path1, path2, tradeWindow) {
    tradeWindow.window.close();
    path1.classList.remove("pathFrom");
    path2.classList.remove("pathTo");
    path2.classList.remove("AdjacentPath");
}

function closePlaceWindow(path, placeWindow) {
    placeWindow.window.close();
    path.classList.remove("SelectedPath");
}




function trade(value, path1, path2) {
    let countries = require('../assets/dataStates.json').map.continents.territories;
    let from = countries[path1.attributes.title.value];
    let to = countries[path2.attributes.title.value];
    console.log(from, to, value);

    /*
    if (from != null && from.Armies > value && to != null && value <= 3 && value >= 1 && from.Armies > 1) {
        //to do after with backend
        //console.log(from, to);
        //console.log(from.Armies);
        from.Armies -= value;
        to.Armies += value;
        //console.log("trade successful");
        countries[path1.attributes.title.value].Armies = from.Armies;
        countries[path2.attributes.title.value].Armies = to.Armies;

    }
    else {
        //console.log("trade failed");

    }
    */
}
