import React, { Component } from 'react';
// import {Table} from 'reactstrap';


class Recipe extends Component{

    constructor(props){
        super(props);
        this.state = {recipe : undefined,isLoading : true};
    }

    async componentDidMount(){
        this.setState({isLoading: true})
        fetch('api/yep?id=2')
            .then(response => response.json())
            .then(data => this.setState({recipe: data, isLoading: false}));
    }

    render() {
        const {recipe, isLoading} = this.state;

        if(isLoading){
            return <p>Loading recipe...</p>
        }

        const recipeIngredient = recipe.ingredientList.map(recipeIngredient=>{
            let ingredientName = recipeIngredient.ingredient.name;
            let amount = recipeIngredient.amount.value;
            return (
                <tr>{amount} gram {ingredientName}</tr>
            )
        });

        let i = 1;
        const recipeInstructions = recipe.instructions.map(instruction =>{
            return <tr>{`${i++}. ${instruction}`}</tr>
        })

        const recipeDivDing = (
            <table className="recipeTable">
                <h2><tr>{recipe.name}</tr></h2>
                <tr><h3>Ingredients:</h3></tr>
                {recipeIngredient}
                <tr><h3>Instructions:</h3></tr>
                {recipeInstructions}
            </table>
        );

        return <div>
            {recipeDivDing}
        </div>;
    }
}

export default Recipe;