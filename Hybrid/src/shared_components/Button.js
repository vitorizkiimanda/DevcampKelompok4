import React from 'react';
import {
	TouchableWithoutFeedback,
	View,
	Text,
	StyleSheet
} from 'react-native';
import { colors } from '../resources/values/styles';
import PropTypes from 'prop-types';

class Button extends React.Component {
	static propTypes = {
		onPress: PropTypes.func.isRequired,
		style: PropTypes.object,
		disabled: PropTypes.bool,
		title: PropTypes.string.isRequired,
		small: PropTypes.bool,
		variant: PropTypes.oneOf(['primary', 'secondary', 'outline', 'secondaryOutline', 'disabled'])
	}

	static defaultProps = {
		small: false,
		disabled: false,
		style: {},
		variant: 'primary'
	}

	constructor(props) {
		super(props);
		this.state = {
			isPressed: false
		};
	}

	press = () => {
		this.setState({
			isPressed: true
		});
	}

	release = () => {
		this.setState({
			isPressed: false
		});
	}

	render() {
		const {
			small,
			onPress,
			title,
			variant,
			disabled,
			style
		} = this.props;

		const { isPressed } = this.state;

		let baseStyle = styles.common;
		if (small) {
			baseStyle = { ...styles.common, ...styles.small };
		}

		let normal;
		let pressed;
		switch (variant) {
			case 'secondary':
				normal = styles.secondary;
				pressed = styles.secondaryPressed;
				break;
			case 'disabled':
				normal = styles.secondaryPressed;
				pressed = styles.secondaryPressed;
				break;
			case 'secondaryOutline':
				normal = {
					...styles.secondaryOutline,
					borderWidth: 1,
					paddingVertical: baseStyle.paddingVertical - 1
				};
				pressed = styles.secondaryOutlinePressed;
				break;
			case 'outline':
				normal = {
					...styles.outline,
					borderWidth: 1,
					paddingVertical: baseStyle.paddingVertical - 1
				};
				pressed = {
					...styles.outlinePressed,
					borderWidth: 2,
					paddingVertical: baseStyle.paddingVertical - 2
				};
				break;
			default:
				normal = styles.primary;
				pressed = styles.primaryPressed;
		}

		let textStyle = styles.text;
		if (variant === 'outline') {
			textStyle = { ...styles.text, ...styles.textOutline };
		}

		if (variant === 'secondary') {
			textStyle = { ...styles.text, ...styles.textSecondary };
		}

		if (variant === 'secondaryOutline' || variant === 'secondaryOutlinePressed') {
			textStyle = { ...styles.text, ...styles.textSecondaryOutline };
		}

		if (disabled) {
			normal = styles.disabled;
			textStyle = { ...styles.text, ...styles.textDisabled };
		}

		return (
			<TouchableWithoutFeedback
				disabled={disabled}
				onPressIn={this.press}
				onPressOut={this.release}
				onPress={onPress}>
				<View
					style={{
						...baseStyle,
						...isPressed ? pressed : normal,
						...style
					}}>
					<Text style={textStyle}>{small ? title : title}</Text>
				</View>
			</TouchableWithoutFeedback>
		);
	}
}

const styles = StyleSheet.create({
	common: {
		paddingVertical: 13,
		// borderRadius: 5,
		justifyContent: 'center'
	},
	small: {
		paddingVertical: 6,
		paddingHorizontal: 6
	},
	primary: {
		backgroundColor: colors.primary
	},
	primaryPressed: {
		backgroundColor: '#1a237e'
	},
	secondary: {
		backgroundColor: 'white'
	},
	secondaryPressed: {
		backgroundColor: '#E4ADAB'
	},
	secondaryOutline: {
		borderColor: colors.primary
	},
	secondaryOutlinePressed: {
		borderColor: colors.primary,
		backgroundColor: '#dfdfdf'
	},
	outline: {
		backgroundColor: 'white',
		borderColor: colors.primary
	},
	outlinePressed: {
		backgroundColor: 'white',
		borderColor: '#3177BC'
	},
	disabled: {
		backgroundColor: '#F0F0F0'
	},
	text: {
		fontSize: 14,
		color: 'white',
		fontFamily: 'Roboto',
		fontWeight: '500',
		textAlign: 'center'
	},
	textOutline: {
		color: colors.primary
	},
	textSecondary: {
		color: colors.secondary,
	},
	textSecondaryOutline: {
		color: colors.primary,
	},
	textDisabled: {
		color: '#C4C4C4'
	}
});

export default Button;
