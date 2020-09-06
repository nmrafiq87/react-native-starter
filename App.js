/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { Component } from 'react';
import codePush from "react-native-code-push";
import PreferenceModule from './PreferenceModule';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar,
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';


//Code push policy 
let codePushOptions = {
  checkFrequency: codePush.CheckFrequency.ON_APP_RESUME,
  installMode: codePush.InstallMode.IMMEDIATE,
  // minimumBackgroundDuration: 30*60 // 30 minutes
};

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      contact: {id:"", name: "", age: 0}
    }
  }

  componentDidMount(){
      PreferenceModule.getContact(this.props.id, (
        map => {
          console.log("Name " + map.name)
          this.setState({contact: map})
        }))
  }
  render() {
    console.log("Implement ", this.props.base_url)
    console.log("Initial Properties", this.props.id)
    return (
      <>
      <StatusBar barStyle="dark-content" />
      <SafeAreaView>
        <ScrollView
          contentInsetAdjustmentBehavior="automatic"
          style={styles.scrollView}>
          <View style={styles.body}>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Contact id</Text>
              <Text style={styles.sectionDescription}>
                {this.state.contact.id}
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Contact Name</Text>
              <Text style={styles.sectionDescription}>
                {this.state.contact.name}
              </Text>
            </View>
            <View style={styles.sectionContainer}>
              <Text style={styles.sectionTitle}>Contact Age</Text>
              <Text style={styles.sectionDescription}>
                {this.state.contact.age}
              </Text>
            </View>
          
          </View>
        </ScrollView>
      </SafeAreaView>
    </>
    )
  }
};

const styles = StyleSheet.create({
  scrollView: {
    backgroundColor: Colors.lighter,
  },
  engine: {
    position: 'absolute',
    right: 0,
  },
  body: {
    backgroundColor: Colors.white,
  },
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
    color: Colors.black,
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
    color: Colors.dark,
  },
  highlight: {
    fontWeight: '700',
  },
  footer: {
    color: Colors.dark,
    fontSize: 12,
    fontWeight: '600',
    padding: 4,
    paddingRight: 12,
    textAlign: 'right',
  },
});

export default codePush(codePushOptions)(App);
