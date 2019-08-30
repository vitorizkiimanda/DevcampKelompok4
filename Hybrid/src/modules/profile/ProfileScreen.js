import React, { Component } from 'react'
import { SafeAreaView, Text } from 'react-native'



class ProfileScreen extends Component {

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
        <Text>ProfileScreen</Text>
      </SafeAreaView>
    );
  }
}

export default ProfileScreen

