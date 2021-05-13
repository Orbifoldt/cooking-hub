import React, { Component } from 'react';
import './Recipe.css';

const recipe = {
    id:1,
    name:"Onion soup",
    ingredients:[{
        ingredient:{
            id:1,
            name:"Onion"
        },
        unit:{
            amount:800,
            type:"MASS"
        },
        ordinal:0
    },
    {
        ingredient:{
            id:2,
            name:"Stock"
        },
        unit:{
            amount:500,
            type:"VOLUME"
        },
        ordinal:1
    },
    {
        ingredient:{
            id:3,
            name:"Garlic"
        },
        unit:{
            amount:3,
            type:"VOLUME",
            unitName:"cloves"
        },
        ordinal:2
    }],
    instructions:[{
        ordinal:0,
        instruction:"Slice the onion and cook on medium untill browned deeply, circa 40 minutes. Stir occasionally."
    },
    {
        ordinal:1,
        instruction:"Dice the garlic in the meantime. Add to the onion."
    },
    {
        ordinal:2,
        instruction:"Deglaze with the stock. Let simmer for 1 hour."
    }]
};


class Recipe extends Component{

    constructor(props){
        super(props);
        console.log("Constructor has been called!")
    } 

    Title(){
        return <div name="recipe-title"><h1>{recipe.name}</h1></div>;
    }


    Ingredient = (props) => {   
        return <li>{props.recipeIngredient.unit.amount} units of {props.recipeIngredient.ingredient.name}</li>;
    }

    Ingredients = (props) => {
        const Ingredient = this.Ingredient;
        let ingredients = recipe.ingredients.map((recipeIngredient) => <Ingredient recipeIngredient={recipeIngredient}/>)
        return <div name="ingredients">
                <h3>Ingredients</h3>
                <ul>{ingredients}</ul>
            </div>;
    }


    Instruction = (props) => {
        return <li>{props.instruction.ordinal + 1}. {props.instruction.instruction}</li>
    }

    Instructions = (props) => {
        let instructions = recipe.instructions.map((recipeInstruction) => <this.Instruction instruction={recipeInstruction}/>)
        return <div name="instructions">
                <h3>Instructions</h3>
                <ul>{instructions}</ul>
            </div>;
    }


    render(){
        const recipeElement = 
        <div className="Recipe">
            <this.Title />
            <this.Ingredients />
            <this.Instructions />
        </div>;
        return recipeElement;
    }

}


export default Recipe;