import React from 'react';
import Sunburst from 'react-sunburst-d3-v4';

export default class CurrencyZoomableSunburst extends React.Component {
    onSelect(event) {
        console.log("CurrencyZoomableSunburst onSelect: " + event);
    }

    render() {
        return (
            <div>
                <Sunburst
                    data={this.props.data}
                    onSelect={this.onSelect}
                    scale="linear"
                    tooltipContent={<div className="sunburstTooltip"/>}
                    tooltip
                    tooltipPosition="right"
                    keyId="anagraph"
                    width="275"
                    height="250"
                />
            </div>);
    }
}