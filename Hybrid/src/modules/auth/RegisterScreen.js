import React, { Component } from 'react'
import { SafeAreaView, Text } from 'react-native'



class RegisterScreen extends Component {

  constructor(props) {
    super(props);
    this.state = {
    };
  }


  render() {
    return (
      <SafeAreaView
        style={{
          width: '100%',
          flex: 1,
          paddingHorizontal: 10.5,
        }}>
        <Text>RegisterScreen</Text>
      </SafeAreaView>
    );
  }
}

export default RegisterScreen

