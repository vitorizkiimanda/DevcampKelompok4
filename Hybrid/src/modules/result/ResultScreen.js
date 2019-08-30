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
class ResultScreen extends Component {

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
    Tts.speak('Selamat datang di halaman hasil transaksi investasi')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tombol halaman bantuan berada di bagian kanan atas')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tekan sekali untuk mengetahui keterangan sebuah tombol')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Kemudian tekan dua kali untuk menjalankan fungsi tertentu dari sebuah tombol ')
  }

  about() {
    Tts.stop()
    Tts.speak('Halaman Hasil transaksi investasi berisi informasi transaksi investasi yang telah dilakukan ')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol kembali berada di kiri atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol bantuan berada di kanan atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol hasil transaksi investasi berada di bagian tengah layar')
    // this.delay(DELAY_TIME)
    // Tts.speak('Tombol konfirmasi  emas berada di kiri bawah layar')
    // this.delay(DELAY_TIME)
    // Tts.speak('Tombol penambahan emas berada di kanan bawah layar')
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
            title="KEMBALI"
            style={{
              flex: 1,
              backgroundColor: 'purple'
            }}
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                this.props.navigation.goBack()
              } else {
                Tts.stop()
                Tts.speak(`Tombol kembali`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan tombol dua kali untuk kembali ke halaman berikutnya`)
              }
              this.lastPress = time
            }}
          />
          <Button
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
            title="HASIL TRANSAKSI INVESTASI"
            onPress={() => {

              // double tap happend
              Tts.stop()
              this.delay(DELAY_TIME)
              Tts.speak(`Transaksi investasi berhasil dilakukan`)
              this.delay(DELAY_TIME)
              Tts.speak(`Transaksi investasi gagal dilakukan)`)
              this.delay(DELAY_TIME)
              Tts.speak(`Tekan tombol kembali untuk kembali ke halaman transaksi`)
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
        </View>
      </SafeAreaView>
    );
  }
}

export default ResultScreen

