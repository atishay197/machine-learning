function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;
grad = zeros(size(theta));

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

powr = -1.*X*theta;
H = 1 ./(1 + exp(powr));
diff = H - y;
sumTerm1 =  sum(-1.*y.*log(H)-(-1.*y + 1).*log(-1.*H + 1))/m;
ts = theta((2:end),1)
sumTerm2 = lambda/(2*m)*sum(ts.^2);
J = sumTerm1+sumTerm2;
dimension = size(theta);
Term1 = (sum(diff(:,ones(1,dimension(1))).*X))/m;
grad = Term1 + (lambda/m*theta)';
grad(1) = Term1(1);


% =============================================================

end
