import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  static const platform = MethodChannel('com.example.myapp/methodChannel');

  String _message = 'No message yet';
  bool secondMessage=false;
  void _getAndroidMessage() async {
    final message = await platform.invokeMethod('getMessageFromAndroid');
    final secondMessageTwo = await platform.invokeMethod('getMessageFromAndroidSecond');
    if(secondMessage==false){
      try {
        setState(() {
          _message = message;
        });
      } catch (e) {
        print('Error: $e');
      }
    }else{
      try {
        setState(() {
          _message = secondMessageTwo;
        });
      } catch (e) {
        print('Error: $e');
      }
    }
setState(() {

});
  }

  @override
  void initState() {
    super.initState();
    // _getAndroidMessage();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Flutter MainActivity Integration'),
        ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Message from MainActivity:',
                style: TextStyle(fontSize: 18),
              ),
              Text(
                _message,
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
              ),
              ElevatedButton(onPressed: (){
                secondMessage=!secondMessage;
                print(secondMessage);
                _getAndroidMessage();
              }, child: Text('Change word'))
            ],
          ),
        ),
      ),
    );
  }
}
