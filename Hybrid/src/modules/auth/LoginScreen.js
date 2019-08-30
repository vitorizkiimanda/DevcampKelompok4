import React, {
  Component,
} from 'react'
import {
  SafeAreaView,
  View,
  Dimensions
} from 'react-native'
import Button from '../../shared_components/Button'
import Typography from '../../shared_components/Typography'

import Tts from 'react-native-tts'
import Voice from 'react-native-voice'
import { colors } from '../../resources/values/styles';


const DELAY_TIME = 1000
class LoginScreen extends Component {

  constructor(props) {
    super(props);
    this.state = {
      username: "",
      password: "",
      results: [],
      onRecording: false,
      timePassed: false
    }

    Voice.onSpeechResults = this.onSpeechResults.bind(this)
    Tts.setDefaultLanguage('id-ID');
  }

  lastPress = 0

  onSpeechStart() {
    Voice.start('id-ID')
  }

  componentDidMount() {
    Tts.speak('Selamat datang di halaman Login')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tombol halaman bantuan berada di bagian kanan atas')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tekan sekali untuk mengetahui keterangan sebuah tombol')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Kemudian tekan dua kali untuk menjalankan fungsi tertentu dari sebuah tombol ')
  }

  about() {
    Tts.stop()
    Tts.speak('Halaman login terdiri atas tombol untuk merekam username dan password ')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol daftar berada di kiri atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol bantuan berada di kanan atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol masuk berada di bagian tengah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol password berada di kanan bawah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol username berada di kiri bawah layar')
  }


  usernameButton() {
    Tts.stop()
    const {
      onRecording,
      results
    } = this.state

    if (onRecording) {
      if (results) {
        Tts.speak(`Username berhasil disimpan`)
        console.log(results)
        this.setState({
          username: results[0]
        })
        this.setState({
          results: []
        })
      } else {
        Tts.speak(`Username gagal disimpan`)
      }
      this.onSpeechEnd()
    } else {
      this.onSpeechStart()
    }
    this.setState({
      onRecording: !onRecording
    })
  }

  passwordButton() {
    const {
      results,
      password,
      onRecording
    } = this.state
    Tts.stop()
    if (onRecording) {
      this.onSpeechEnd()
      this.setState({
        password: results ? results[0] : ""
      })
      Tts.speak(`Password ${password} berhasil disimpan`)
    } else {
      this.onSpeechStart()
    }
    this.setState({
      onRecording: !onRecording
    })
  }

  delay(time) {
    setTimeout(() => { this.setState({ timePassed: true }) }, time)
  }

  onSpeechEnd() {
    Voice.stop()
  }

  onSpeechResults(e) {
    this.setState({
      results: e.value
    })
  }

  componentWillUnmount() {
    Tts.stop()
  }
  render() {
    const {
      username,
      results,
      onRecording,
      password,
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
            height: Dimensions.get('window').height / 3
          }}
        >
          <Button
            disabled={onRecording}
            style={{
              flex: 1,
              backgroundColor: 'purple'
            }}
            title="DAFTAR"
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                // this.props.navigation.navigate('Home')
                this.props.navigation.navigate('Register')
              } else {
                Tts.stop()
                Tts.speak(`Tombol Mendaftar`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk pindah ke halaman daftar`)
              }
              this.lastPress = time
            }}
          />
          <Button
            disabled={onRecording}
            title="BANTUAN"
            style={{
              flex: 1,
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
            title="MASUK"
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.speak(`Tombol Masuk`)

                this.props.navigation.navigate('Home')
              } else {
                Tts.stop()
                Tts.speak(`Tombol masuk`)
                this.delay(DELAY_TIME)
                if (username) {
                  Tts.speak(`Username terekam adalah ${username}`)
                } else {
                  Tts.speak(`Belum ada username yang terekam`)
                }
                this.delay(DELAY_TIME)
                if (password) {
                  Tts.speak(`Password terekam adalah ${password}`)
                } else {
                  Tts.speak(`Belum ada password terekam`)
                }
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk melakukan login dengan username dan password yang telah direkam`)
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
            title={onRecording ? "Recording..." : "USERNAME"}
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                this.usernameButton()
              } else {
                Tts.stop()
                if (username) {
                  Tts.speak(`nama pengguna adalah ${username}`)
                } else {
                  Tts.speak(`Tombol Username`)
                  this.delay(DELAY_TIME)
                  Tts.speak(`tekan dua kali untuk merekam nama pengguna`)
                  this.delay(DELAY_TIME)
                  Tts.speak(`kemudian tekan dua kali untuk berhenti merekam`)
                }
              }
              this.lastPress = time
            }}
          />

          <Button
            style={{
              flex: 1,
              backgroundColor: 'blue'
            }}
            title={onRecording ? "Recording..." : "PASSWORD"}
            onPress={async () => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                this.passwordButton()
              } else {
                Tts.stop()
                Tts.speak(`Tombol Password`)
                this.delay(DELAY_TIME)
                if (password) {
                  Tts.speak(`password pengguna adalah ${password}`)
                } else {
                  Tts.speak(`tekan dua kali untuk merekam password pengguna`)
                  this.delay(DELAY_TIME)
                  Tts.speak(`kemudian tekan dua kali untuk berhenti merekam`)
                }
              }
              this.lastPress = time
            }}
          />
        </View>
      </SafeAreaView>
    );
  }
}

export default LoginScreen

