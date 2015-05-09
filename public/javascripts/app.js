var types = ['Single line text', 'Text area', 'Radio button', 'Check box', 'Combo box', 'Date'];
var moduleApp = angular.module('moduleApp', ['ngRoute']);

moduleApp.config(["$routeProvider", function ($routeProvider) {
      return $routeProvider.when("/fields", {
        templateUrl: "/views/fieldsList",
        controller: "FieldsListController"
      }).when("/create", {
        templateUrl: "/views/fieldCreate",
        controller: "FieldCreateController"
      }).when("/field/:id", {
        templateUrl: "/views/fieldEdit",
        controller: "FieldEditController"
      }).when("/responses", {
        templateUrl: "/views/responsesList",
        controller: "ResponsesListController"
      }).when("/createResponse", {
          templateUrl: "/views/responseCreate",
          controller: "ResponseCreateController"
      }).otherwise({
        redirectTo: "/fields"
      });
    }])
    .config([
      "$locationProvider", function ($locationProvider) {
        return $locationProvider.html5Mode(true).hashPrefix("!");
      }
    ]);

moduleApp.controller("FieldsListController", ["$scope", "$location", "$http", "$route", function ($scope, $location, $http, $route) {
  $http.get('/field').
      success(function (data, status, headers, config) {
        $scope.fields = data;
      });
  $scope.type = types;
  $scope.addField = function () {
    $location.path('/create');
  };
  $scope.editField = function (field) {
    $location.path('/field/' + field.id);
  };
  $scope.delField = function (field) {
    $http.delete('/field/' + field.id).success($route.reload());
  };
    $scope.responsesList = function () {
        $location.path('/responses');
    }
}]);

moduleApp.controller("FieldCreateController", ["$scope", "$location", "$http", function ($scope, $location, $http) {
  $scope.item = {label: null, type: null, required: false, active: false};
  $scope.types = types;
  $scope.saveField = function () {
    if ($scope.item.label == null || $scope.item.type == null) {
      alert("Fields are not corrected");
      return;
    }
      $http.post('/field', $scope.item).
          success(function (data, status, headers, config) {
              $location.path('/fields');
          });
  };
}]);

moduleApp.controller("FieldEditController", ["$scope", "$routeParams", "$location", "$http", function ($scope, $routeParams, $location, $http) {
  $scope.types = types;
    $http.get('/field/' + $routeParams.id)
        .success(function (data, status, headers, config) {
            var opt = [];
            for (var i = 0; i < data.options.length; i++) {
                opt.push(data.options[i].text);
            }
            $scope.item ={id: data.id, label: data.label, type: data.type,
                required: data.required, active: data.active, options: opt};
        });
  $scope.updateField = function () {
      if ($scope.item.label == null || $scope.item.type == null) {
          alert("Fields are not corrected");
          return;
      }
      $http.post('/field/' + $scope.item.id, $scope.item).
          success(function (data, status, headers, config) {
              $location.path('/fields');
          });
  }
}]);

moduleApp.controller("ResponsesListController", ["$scope", "$location", "$http", "$route", function ($scope, $location, $http, $route) {
    $http.get('/response').
        success(function (data, status, headers, config) {
            $scope.responses = data;
        });
    $scope.fieldsList = function () {
        $location.path('/fields');
    }
    $scope.addResponse = function () {
        $location.path('/createResponse');
    };
}]);

moduleApp.controller("ResponseCreateController", ["$scope", "$location", "$http", function ($scope, $location, $http) {
    $scope.item = {label: null, type: null, required: false, active: false};
    $scope.saveField = function () {
        if ($scope.item.label == null || $scope.item.type == null) {
            alert("Fields are not corrected");
            return;
        }
        $http.post('/field', $scope.item).
            success(function (data, status, headers, config) {
                $location.path('/fields');
            });
    };
}]);