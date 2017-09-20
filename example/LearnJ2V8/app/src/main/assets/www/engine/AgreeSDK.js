;(function() {
    // 内部使用的全局函数require/define
    var require,
        define;

    (function () {
        // 初始化一个空对象，缓存所有的模块
        var modules = {},
        // 正在build中的模块ID的栈
        requireStack = [],
        // 标示正在build中模块ID的Map
        inProgressModules = {},
        SEPARATOR = ".";

        // 模块build
        function build(module) {
            // 备份工厂方法
            var factory = module.factory,
            // 对require对象进行特殊处理
            localRequire = function (id) {
                var resultantId = id;
                //Its a relative path, so lop off the last portion and add the id (minus "./")
                if (id.charAt(0) === ".") {
                    resultantId = module.id.slice(0, module.id.lastIndexOf(SEPARATOR)) + SEPARATOR + id.slice(2);
                }
                return require(resultantId);
            };
            // 给模块定义一个空的exports对象，防止工厂类方法中的空引用
            module.exports = {};
            // 删除工厂方法
            delete module.factory;
            // 调用备份的工厂方法（参数必须是require,exports,module）
            factory(localRequire, module.exports, module);
            // 返回工厂方法中实现的module.exports对象
            return module.exports;
        }

        // 加载使用模块
        require = function (id) {
            // 如果模块不存在抛出异常
            if (!modules[id]) {
                throw "module " + id + " not found";
            // 如果模块正在build中抛出异常
            } else if (id in inProgressModules) {
                var cycle = requireStack.slice(inProgressModules[id]).join('->') + '->' + id;
                throw "Cycle in require graph: " + cycle;
            }
            // 如果模块存在工厂方法说明还未进行build（require嵌套）
            if (modules[id].factory) {
                try {
                    // 标示该模块正在build
                    inProgressModules[id] = requireStack.length;
                    // 将该模块压入请求栈
                    requireStack.push(id);
                    // 模块build，成功后返回module.exports
                    return build(modules[id]);
                } finally {
                    // build完成后删除当前请求
                    delete inProgressModules[id];
                    requireStack.pop();
                }
            }
            // build完的模块直接返回module.exports
            return modules[id].exports;
        };

        // 定义注册模块
        define = function (id, factory) {
            // 如果已经存在抛出异常
            if (modules[id]) {
                throw "module " + id + " already defined";
            }
            // 模块以ID为索引包含ID和工厂方法
            modules[id] = {
                id: id,
                factory: factory
            };
        };

        // 移除模块
        define.remove = function (id) {
            delete modules[id];
        };

        // 返回所有模块
        define.moduleMap = modules;
    })();

    define("AgreeSDK", function(require, exports, module) {
        var AgreeSDK = {
            require: require,
            define: define
        }
        module.exports = AgreeSDK;
    }

    window.AgreeSDK = require("AgreeSDK");
})();