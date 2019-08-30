import React from 'react';
import {
	View
} from 'react-native';
import { TextField } from 'react-native-material-textfield';
import PropTypes from 'prop-types';
import { colors } from '../resources/values/styles';

const TextInput = ({
	label,
	error,
	value,
	onChangeText,
	style,
	icon,
	placeholder,
	containerStyle,
	labelTextStyle,
	labelFontSize,
	disabled,
	prefix,
	suffix,
	onBlur,
	keyboardType
}) => (
		<View style={{ flexDirection: 'row', alignItems: 'center', ...style }}>
			{icon && (
				<View style={{ width: 24, justifyContent: 'flex-start' }}>
					{React.cloneElement(
						icon,
						{
							style: {
								color: "#999999",
								paddingTop: 10
							},
							size: 16
						}
					)}
				</View>
			)}
			<TextField
				keyboardType={keyboardType}
				prefix={prefix}
				suffix={suffix}
				label={label}
				value={value}
				error={error}
				labelFontSize={labelFontSize}
				onChangeText={onChangeText}
				textColor="#555555"
				baseColor="#555555"
				tintColor={colors.primary}
				labelPadding={4}
				labelTextStyle={labelTextStyle}
				labelFontSize={labelFontSize}
				placeholder={placeholder}
				fontSize={14}
				labelHeight={20}
				disabled={disabled}
				onBlur={onBlur}
				// inputContainerPadding={8}
				containerStyle={containerStyle}
			/>
		</View>
	);

TextInput.propTypes = {
	label: PropTypes.string,
	value: PropTypes.string,
	onChangeText: PropTypes.func,
	style: PropTypes.object,
	icon: PropTypes.node,
	error: PropTypes.string
};

TextInput.defaultProps = {
	label: '',
	value: '',
	onChangeText: undefined,
	style: undefined,
	error: undefined,
	icon: null
};

export default TextInput;
