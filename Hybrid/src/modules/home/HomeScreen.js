import React, {
  Component,
} from 'react'
import {
  SafeAreaView,
  View,
  Dimensions
} from 'react-native'
import Button from '../../shared_components/Button'

import Tts from 'react-native-tts'
import Voice from 'react-native-voice'


const DELAY_TIME = 1000
class HomeScreen extends Component {

  constructor(props) {
    super(props);
    this.state = {
      onRecording: false,
      timePassed: false
    }

    Tts.setDefaultLanguage('id-ID');
  }

  lastPress = 0

  onSpeechStart() {
    Voice.start('id-ID')
  }

  componentDidMount() {
    Tts.speak('Selamat datang di halaman Beranda')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol halaman bantuan berada di bagian kanan atas')
    this.delay(DELAY_TIME)
    Tts.speak('Tekan sekali untuk mengetahui keterangan sebuah tombol')
    this.delay(DELAY_TIME)
    Tts.speak('Kemudian tekan dua kali untuk menjalankan fungsi tertentu dari sebuah tombol ')
  }

  about() {
    Tts.stop()
    Tts.speak('Halaman Beranda terdiri atas tombol tombol untuk melakukan investasi ')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol bantuan berada di kanan atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol emas berada di bagian tengah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol reksadana berada di kiri bawah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol lainnya berada di kanan bawah layar')
  }


  delay(time) {
    setTimeout(() => { this.setState({ timePassed: true }) }, time)
  }


  componentWillUnmount() {
    Tts.stop()
  }
  render() {
    const {
      onRecording,
    } = this.state
    return (
      <SafeAreaView
        style={{
          width: '100%',
          flex: 1,
        }}>
        <View
          style={{
            width: '100%',
            flexDirection: 'row',

            justifyContent: 'flex-end',
            height: Dimensions.get('window').height / 3
          }}
        >
          <Button
            disabled={onRecording}
            title="BANTUAN"
            style={{
              width: '50%',
              height: '100%',
              backgroundColor: 'green'
            }}
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                this.about()
              } else {
                Tts.stop()
                Tts.speak(`Tombol bantuan`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan tombol bantuan untuk mengetahui daftar tombol yang terdapat pada layar`)
              }
              this.lastPress = time
            }}
          />
        </View>

        <View
          style={{
            width: '100%',
            flexDirection: 'row',
            height: Dimensions.get('window').height / 3
          }}
        >
          <Button
            style={{
              flex: 1,
              backgroundColor: 'orange'
            }}
            disabled={onRecording}
            title="EMAS"
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                this.props.navigation.navigate('InvestasiEmas')
              } else {
                Tts.stop()
                Tts.speak(`Tombol EMAS`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk masuk ke layanan emas`)
                this.delay(DELAY_TIME)
              }
              this.lastPress = time
            }}
          />
        </View>

        <View
          style={{
            width: '100%',
            flexDirection: 'row',
            height: Dimensions.get('window').height / 3
          }}
        >
          <Button
            style={{
              flex: 1,
              backgroundColor: 'red'
            }}
            title={onRecording ? "Recording..." : "REKSADANA"}
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                this.props.navigation.navigate('InvestasiReksadana')
              } else {
                Tts.stop()
                Tts.speak(`Tombol Reksadana`)
                this.delay(DELAY_TIME)
                Tts.speak(`tekan dua kali untuk masuk pada layanan reksadana`)
                this.delay(DELAY_TIME)
              }
              this.lastPress = time
            }}
          />

          <Button
            style={{
              flex: 1,
              backgroundColor: 'blue'
            }}
            title={onRecording ? "Recording..." : "LAINNYA"}
            onPress={async () => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                this.props.navigation.navigate('InvestasiLainnya')
              } else {
                Tts.stop()
                Tts.speak(`Tombol Lainnya`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk melihat menu lainnya`)
              }
              this.lastPress = time
            }}
          />
        </View>
      </SafeAreaView>
    );
  }
}

export default HomeScreen

