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
class BeliEmasScreen extends Component {

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
    Tts.speak('Selamat datang di halaman pembelian emas')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tombol halaman bantuan berada di bagian kanan atas')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Tekan sekali untuk mengetahui keterangan sebuah tombol')
    setTimeout(() => { this.setState({ timePassed: true }) }, 1000)
    Tts.speak('Kemudian tekan dua kali untuk menjalankan fungsi tertentu dari sebuah tombol ')
  }

  about() {
    Tts.stop()
    Tts.speak('Halaman Pembelian Emas terdiri atas tombol tombol untuk melakukan pembelian emas ')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol kembali berada di kiri atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol bantuan berada di kanan atas layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol konfirmasi pembelian emas berada di bagian tengah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol pengurangan emas berada di kiri bawah layar')
    this.delay(DELAY_TIME)
    Tts.speak('Tombol penambahan emas berada di kanan bawah layar')
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
              flex:1,
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
              flex:1,
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
            title="KONFIRMASI PEMBELIAN EMAS"
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                this.props.navigation.navigate('Result')
              } else {
                Tts.stop()
                Tts.speak(`Tombol konfirmasi pembelian emas`)
                this.delay(DELAY_TIME)
                Tts.speak(`Jumlah emas yang akan anda beli adalah sebesar 3 gram dengan total harga Rp. 50.000`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk melakukan konfirmasi pembelian emas `)
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
            title={onRecording ? "Recording..." : "KURANGI EMAS"}
            onPress={() => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                // this.props.navigation.navigate('InvestasiReksadana')
                // this.props.navigation.navigate('JualEmas')
              } else {
                Tts.stop()
                Tts.speak(`Tombol Pengurangan ukuran pembelian Emas`)
                this.delay(DELAY_TIME)
                Tts.speak(`tekan dua kali untuk melakukan pengurangan ukuran pembelian emas`)
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
            title={onRecording ? "Recording..." : "TAMBAH EMAS"}
            onPress={async () => {
              const time = new Date().getTime();
              const delta = time - this.lastPress;
              const DOUBLE_PRESS_DELAY = 400;

              if (delta < DOUBLE_PRESS_DELAY) {
                // double tap happend
                Tts.stop()
                // this.props.navigation.navigate('BeliEmas')
              } else {
                Tts.stop()
                Tts.speak(`Tombol Penambahan ukuran emas`)
                this.delay(DELAY_TIME)
                Tts.speak(`Tekan dua kali untuk melakukan penambahan ukuran pembelian emas`)
              }
              this.lastPress = time
            }}
          />
        </View>
      </SafeAreaView>
    );
  }
}

export default BeliEmasScreen

