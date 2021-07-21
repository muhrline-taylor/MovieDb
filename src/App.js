import Header from "./components/Header";
import "./static/css/App.css";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import ViewAllMovies from "./components/ViewAllMovies";
import ViewAllDirectors from "./components/ViewAllDirectors";
import ViewAllActors from "./components/ViewAllActors";
import ViewOneMovie from "./components/ViewOneMovie";
import ViewOneDirector from "./components/ViewOneDirector";
import ViewOneActor from "./components/ViewOneActor";
import CreateDirector from "./components/CreateDirector";
import CreateActor from "./components/CreateActor";
import CreateMovie from "./components/CreateMovie";

function App() {
  return (
    <div className="app">
      <div className="app__header">
        <Header />
      </div>

      <div className="app__body">

        <Router>

          <Switch>
            <Route exact path="/movies/new" component={CreateMovie}></Route>
            <Route exact path="/movies/:id" component={ViewOneMovie}></Route>
            <Route exact path="/movies" component={ViewAllMovies}></Route>
          </Switch>
          
          
          


          <Switch>
            <Route exact path="/directors/new" component={CreateDirector}></Route>
            <Route exact path="/directors/:id" component={ViewOneDirector}></Route>
            <Route exact path="/directors" component={ViewAllDirectors}></Route>
          </Switch>
          
          
          
          


          <Switch>
            <Route exact path="/actors/new" component={CreateActor}></Route>
            <Route exact path="/actors/:id" component={ViewOneActor}></Route>
            <Route exact path="/actors" component={ViewAllActors}></Route>
          </Switch>
          



        </Router>



      </div>
      
    </div>
  );
}

export default App;
