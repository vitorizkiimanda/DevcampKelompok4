import React from 'react';
import {
	Text,
	StyleSheet
} from 'react-native';
import PropTypes from 'prop-types';
import { colors } from '../resources/values/styles';

const Typography = props => {
	const {
		variant,
		style,
		children,
		...remainingProps
	} = props;

	return (
		<Text
			style={{ ...styles.base, ...styles[variant], ...style }}
			{...remainingProps}>
			{children}
		</Text>
	);
};

Typography.propTypes = {
	children: PropTypes.node,
	variant: PropTypes.oneOf([
		'title',
		'h1',
		'h2',
		'body',
		'strong'
	]),
	style: PropTypes.object
};

Typography.defaultProps = {
	children: null,
	variant: 'body',
	style: {
		color: 'black'
	}
};

const styles = StyleSheet.create({
	base: {
		fontFamily: 'Circular Std'
	},
	h1: {
		fontSize: 26,
		color: colors.primary,
		fontWeight: '500'
	},
	h2: {
		fontSize: 14,
		color: '#555555'
	},
	title: {
		fontSize: 16,
		color: '#222222',
		fontWeight: '500'
	},
	body: {
		color: '#555555'
	},
	strong: {
		color: '#222222',
		fontWeight: '500'
	}
});

export default Typography;
