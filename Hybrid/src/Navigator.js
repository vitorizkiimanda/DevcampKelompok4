import {
  createAppContainer,
  createStackNavigator,
  createSwitchNavigator
} from "react-navigation"

import React from "react"
import {
  defaultHeaderBar,
} from "./resources/values/styles"

import Login from './modules/auth/LoginScreen'
import Register from './modules/auth/RegisterScreen'
import InvestasiEmas from './modules/gold_invest/InvestasiEmasScreen'
import Home from './modules/home/HomeScreen'
import Profile from './modules/profile/ProfileScreen'
import InvestasiReksadana from './modules/reksadana_invest/InvestasiReksadanaScreen'
import InvestasiLainnya from './modules/investasi_lainnya/InvestasiLainnyaScreen'
import BeliEmas from './modules/gold_invest/BeliEmasScreen'
import JualEmas from './modules/gold_invest/JualEmasScreen'
import Result from "./modules/result/ResultScreen"


const AuthStack = createStackNavigator(
  {
    Login : {
      screen : Login
    },
    Register: {
      screen : Register
    }
  },
  {
    initialRouteName: "Login",
    defaultNavigationOptions: defaultHeaderBar
  }
)

const AppStack = createStackNavigator(
  {
    Home : {
      screen : Home
    },
    Profile: {
      screen : Profile
    },
    InvestasiEmas: {
      screen: InvestasiEmas
    },
    InvestasiReksadana: {
      screen: InvestasiReksadana
    },
    InvestasiLainnya: {
      screen: InvestasiLainnya
    },
    BeliEmas: {
      screen: BeliEmas
    },
    JualEmas: {
      screen: JualEmas
    },
    Result: {
      screen: Result
    }
  },
  {
    initialRouteName: "Home",
    defaultNavigationOptions: defaultHeaderBar
  }
)

export const AppNavigator = createSwitchNavigator(
  {
    AuthStack : AuthStack,
    AppStack : AppStack
  },
  {
    initialRouteName: "AuthStack",
    defaultNavigationOptions: defaultHeaderBar
  }
);

export default createAppContainer(AppNavigator);