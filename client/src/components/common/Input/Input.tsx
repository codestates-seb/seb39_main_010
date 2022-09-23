import React, { HTMLInputTypeAttribute, InputHTMLAttributes } from 'react';
import styled from 'styled-components';

interface StyleProps {
	width?: string;
	height?: string;
	marginBottom?: string;
}

export interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
	type?: HTMLInputTypeAttribute;
	placeholder: string;
	name?: string;
	value?: string;
	onChange?: (e: React.ChangeEvent<HTMLInputElement>) => void;
	label?: string;
	style?: StyleProps;
}

const Input = React.forwardRef<HTMLInputElement, InputProps>(
	(
		{
			type = 'text',
			placeholder,
			name,
			value,
			onChange,
			label,
			...props
		}: InputProps,
		ref
	) => {
		return (
			<InputContainer>
				{label && <label>{label}</label>}
				<StyledInput
					type={type}
					placeholder={placeholder}
					name={name}
					value={value}
					onChange={onChange}
					ref={ref}
					{...props}
				/>
			</InputContainer>
		);
	}
);

Input.displayName = 'Input';

export default Input;

const InputContainer = styled.div`
	display: flex;
	flex-direction: column;

	& label {
		font-size: 18px;
		margin-bottom: 18px;
	}
`;

const StyledInput = styled.input<InputProps>`
	width: ${({ width }) => width ?? '100%'};
	height: ${({ height }) => height ?? '66px'};
	margin-bottom: ${({ style }) => style?.marginBottom ?? '18px'};
	padding: 10px 20px;
	border: 1px solid #d2d5da;
	background-color: white;
	font-size: 18px;
	border-radius: 10px;

	& ::placeholder {
		color: #949494;
	}
`;
